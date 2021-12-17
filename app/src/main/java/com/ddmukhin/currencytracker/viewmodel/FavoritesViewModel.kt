package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FavoritesViewModel @Inject constructor() : ViewModel(), ViewModelContract<FavoritesCurrencyState> {

    private val _state = MutableStateFlow<FavoritesCurrencyState>(FavoritesCurrencyState.Loading)

    override val state: StateFlow<FavoritesCurrencyState> = _state.asStateFlow()

    fun loadCurrencies(){
//        need integration with persistence
    }
}