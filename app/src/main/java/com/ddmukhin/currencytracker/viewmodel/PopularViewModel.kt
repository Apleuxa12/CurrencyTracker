package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.flatMap
import arrow.core.leftIfNull
import com.ddmukhin.currencytracker.data.remote.CurrencyRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.sorted
import com.ddmukhin.currencytracker.utils.getStateAs
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val persistenceRepository: PersistenceRepository
) : ViewModel(), ViewModelContract<PopularCurrencyState> {
    private val _state = MutableStateFlow<PopularCurrencyState>(PopularCurrencyState.Loading)

    override val state: StateFlow<PopularCurrencyState> = _state.asStateFlow()

    private var cachedCurrencyItem: CurrencyItem? = null

    fun updateWithGlobalCurrencyItem(currencyItem: CurrencyItem, sort: List<SortItem<*>>) {
        if (cachedCurrencyItem == null || cachedCurrencyItem!!.base != currencyItem.base) {
            _state.value = PopularCurrencyState.Loading
        }
        cachedCurrencyItem = currencyItem

        viewModelScope.launch {
            val response = currencyRepository.getLatestCurrencies(
                base = currencyItem.base
            )

            val textsResponse = currencyRepository.getSymbolTexts()

            val mappedResponse = textsResponse.flatMap { map ->
                response.map {
                    it.map { response ->
                        CurrencyItem(
                            name = map[response.base] ?: response.base,
                            base = response.base,
                            value = response.value
                        )
                    }
                }
            }

            when (mappedResponse) {
                is Either.Left -> {
                    _state.value = PopularCurrencyState.Error(mappedResponse.value.message)
                }

                is Either.Right -> {
                    var values = mappedResponse.value

                    sort.forEach {
                        values = values.sorted(it)
                    }

                    _state.value = PopularCurrencyState.Success(values)

                    updateFavorites()
                }
            }
        }
    }

    fun addToFavorite(item: CurrencyItem) {
        _state.getStateAsSuccess()?.let { current ->
            viewModelScope.launch {
                persistenceRepository.insert(item)

                _state.value = current.copy(
                    popularCurrencies = current.popularCurrencies.map {
                        if (it == item)
                            it.copy(isFavorite = true)
                        else
                            it
                    }
                )
            }
        }
    }

    fun removeFromFavorites(item: CurrencyItem) {
        _state.getStateAsSuccess()?.let { current ->

            viewModelScope.launch {
                persistenceRepository.delete(item)

                _state.value = current.copy(
                    popularCurrencies = current.popularCurrencies.map {
                        if (it == item)
                            it.copy(isFavorite = false)
                        else
                            it
                    }
                )
            }
        }
    }

    private fun updateFavorites() {
        _state.getStateAsSuccess()?.let { current ->
            viewModelScope.launch {
                val favorites = persistenceRepository.getAll()

                _state.value = current.copy(
                    popularCurrencies = current.popularCurrencies.map { item ->
                        if (favorites.find {
                                it.base == item.base
                            } == null) item else item.copy(isFavorite = true)
                    }
                )
            }
        }
    }

    private fun StateFlow<PopularCurrencyState>.getStateAsSuccess() =
        getStateAs<PopularCurrencyState.Success>()
}