package com.ddmukhin.currencytracker.viewmodel.state

import com.ddmukhin.currencytracker.ui.model.CurrencyItem

sealed class PopularCurrencyState{
    object Loading : PopularCurrencyState()
    data class Error(val errorMessage: String) : PopularCurrencyState()
    data class Success(
        val popularCurrencies: List<CurrencyItem>
    )
}
