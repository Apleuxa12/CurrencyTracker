package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import kotlinx.coroutines.flow.StateFlow

class FavoritesViewModel : ViewModel(), ViewModelContract<FavoritesCurrencyState> {
    override val state: StateFlow<FavoritesCurrencyState>
        get() = TODO("Not yet implemented")
}