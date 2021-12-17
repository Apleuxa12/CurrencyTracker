package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class FavoritesViewModel @Inject constructor() : ViewModel(), ViewModelContract<FavoritesCurrencyState> {
    override val state: StateFlow<FavoritesCurrencyState>
        get() = TODO("Not yet implemented")
}