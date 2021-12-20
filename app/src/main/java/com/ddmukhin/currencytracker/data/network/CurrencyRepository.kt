package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.data.network.model.response.CurrencyItemResponse

interface CurrencyRepository {

    suspend fun getLatestCurrencies(base: String, symbols: List<String> = listOf()): List<CurrencyItemResponse>?

    suspend fun getSymbolTexts(): Map<String, String>?

}