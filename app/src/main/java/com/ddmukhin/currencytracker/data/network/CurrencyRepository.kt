package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.ui.model.CurrencyItem

interface CurrencyRepository {

    suspend fun getLatestCurrencies(base: String, symbols: List<String>): List<CurrencyItem>

    suspend fun getSymbolText(base: String): String

}