package com.ddmukhin.currencytracker.viewmodel.state

import com.ddmukhin.currencytracker.ui.model.CurrencyItem

data class GlobalCurrencyState(
    val globalCurrency: CurrencyItem?,
    val currencies: List<CurrencyItem>
)