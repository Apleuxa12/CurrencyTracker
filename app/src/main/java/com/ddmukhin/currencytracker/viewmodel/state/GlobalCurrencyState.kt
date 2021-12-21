package com.ddmukhin.currencytracker.viewmodel.state

import com.ddmukhin.currencytracker.ui.model.CurrencyItem

data class GlobalCurrencyState(
    val selectedIndex: Int,
    val currencies: List<CurrencyItem>
)