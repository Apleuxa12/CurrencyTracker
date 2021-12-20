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
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val persistenceRepository: PersistenceRepository
) : ViewModel(), ViewModelContract<PopularCurrencyState> {
    private val _state = MutableStateFlow<PopularCurrencyState>(PopularCurrencyState.Loading)

    override val state: StateFlow<PopularCurrencyState> = _state.asStateFlow()

    fun <T : Comparable<T>> applySort(sortItem: SortItem<T>) {
        _state.getStateAsSuccess()?.let { current ->
            _state.value = PopularCurrencyState.Loading

            _state.value =
                current.copy(popularCurrencies = current.popularCurrencies.sorted(sortItem))
        }
    }

    fun updateWithGlobalCurrencyItem(currencyItem: CurrencyItem) {
        _state.value = PopularCurrencyState.Loading

        viewModelScope.launch {
            val response = currencyRepository.getLatestCurrencies(
                base = currencyItem.name
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

            when(mappedResponse){
                is Either.Left -> {
                    _state.value = PopularCurrencyState.Error(mappedResponse.value.message)
                }

                is Either.Right -> {
                    _state.value = PopularCurrencyState.Success(mappedResponse.value)
                }
            }
        }

    }

    fun addToFavorite(base: String) {
        _state.getStateAsSuccess()?.let { current ->

            val item = current.popularCurrencies.find { it.base == base }

            item?.let {
                viewModelScope.launch {
                    persistenceRepository.insert(it)
                }
            }
        }
    }

    fun updateFavorites() {
        _state.getStateAsSuccess()?.let { current ->
            viewModelScope.launch {
                val favorites = persistenceRepository.getAll()

                current.popularCurrencies.forEach { item ->
                    item.isFavorite = favorites.map { it.base }.contains(item.base)
                }

                _state.value = current
            }
        }
    }

    private fun StateFlow<PopularCurrencyState>.getStateAsSuccess() =
        getStateAs<PopularCurrencyState.Success>()
}