package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.CurrencyRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.sorted
import com.ddmukhin.currencytracker.utils.getStateAs
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val persistenceRepository: PersistenceRepository
) : ViewModel(), ViewModelContract<FavoritesCurrencyState> {

    private val _state = MutableStateFlow<FavoritesCurrencyState>(FavoritesCurrencyState.Loading)

    override val state: StateFlow<FavoritesCurrencyState> = _state.asStateFlow()

    fun loadFavorites() {
        _state.value = FavoritesCurrencyState.Loading

        viewModelScope.launch {
            val favorites = persistenceRepository.getAll()

            _state.value = FavoritesCurrencyState.Success(favorites)
        }
    }

    fun <T : Comparable<T>> applySort(sortItem: SortItem<T>) {
        _state.getStateAsSuccess()?.let { current ->
            _state.value = current.copy(list = current.list.sorted(sortItem))
        }
    }

    fun removeFromFavorites(item: CurrencyItem){
        _state.getStateAsSuccess()?.let{ current ->

            viewModelScope.launch {
                persistenceRepository.delete(item)

                val favorites = persistenceRepository.getAll()

                _state.value = current.copy(
                    list = current.list.filter {
                        favorites.contains(it)
                    }
                )
            }
        }
    }

    fun updateWithGlobalCurrency(globalCurrency: CurrencyItem) {
        _state.getStateAsSuccess()?.let { current ->

            viewModelScope.launch {
                val currencies = currencyRepository.getLatestCurrencies(
                    base = globalCurrency.base,
                    symbols = current.list.map { it.base })

                when (currencies) {
                    is Either.Left -> {
//                        do nothing
                    }
                    is Either.Right -> {
                        current.list.forEach { item ->
                            item.value = currencies.value.find { response ->
                                response.base == item.base
                            }?.value ?: 0.0
                        }
                    }
                }

                _state.value = current
            }
        }
    }

    private fun StateFlow<FavoritesCurrencyState>.getStateAsSuccess() =
        getStateAs<FavoritesCurrencyState.Success>()
}