package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.utils.getStateAs
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    fun updateValues(globalCurrency: CurrencyItem) {
        _state.getStateAsSuccess()?.let { current ->

            viewModelScope.launch {
                val currencies = currencyRepository.getLatestCurrencies(
                    base = globalCurrency.base,
                    symbols = current.list.map { it.base })

                currencies?.let { list ->
                    current.list.forEach { item ->
                        item.value = list.find { response ->
                            response.base == item.base
                        }?.value ?: 0.0
                    }
                }

                _state.value = current
            }
        }
    }

    private fun StateFlow<FavoritesCurrencyState>.getStateAsSuccess() =
        getStateAs<FavoritesCurrencyState.Success>()
}