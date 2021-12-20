package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.data.persistence.model.Currency
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

        val textsResponse = viewModelScope.async {
            currencyRepository.getSymbolTexts()
        }

        viewModelScope.launch {
            val response = currencyRepository.getLatestCurrencies(
                base = currencyItem.name
            )

            if (response == null) {
                _state.value = PopularCurrencyState.Error("Ошибка")
            } else {
                val result = mutableListOf<CurrencyItem>()

                val texts = textsResponse.await()

                response.forEach {
                    result.add(
                        CurrencyItem(
                            name = texts?.get(it.base) ?: "",
                            base = it.base,
                            value = it.value
                        )
                    )
                }

                _state.value = PopularCurrencyState.Success(result)
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