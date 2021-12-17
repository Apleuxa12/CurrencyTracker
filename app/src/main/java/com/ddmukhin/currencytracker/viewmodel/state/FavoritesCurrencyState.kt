package com.ddmukhin.currencytracker.viewmodel.state

import com.ddmukhin.currencytracker.ui.model.CurrencyItem

sealed class FavoritesCurrencyState {

    object Loading : FavoritesCurrencyState()
    data class Success(val list: List<CurrencyItem>) : FavoritesCurrencyState()

}