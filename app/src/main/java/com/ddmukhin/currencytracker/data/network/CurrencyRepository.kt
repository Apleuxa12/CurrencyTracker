package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.data.network.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

interface CurrencyRepository {

    suspend fun getLatestCurrencies(base: String, symbols: List<String> = listOf()): List<CurrencyItemResponse>?

    suspend fun getSymbolTexts(): Map<String, String>?

}