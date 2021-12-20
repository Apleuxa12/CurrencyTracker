package com.ddmukhin.currencytracker.data.remote

import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.ui.model.ErrorItem

interface CurrencyRepository {

    suspend fun getLatestCurrencies(base: String = "EUR", symbols: List<String> = listOf()): Either<ErrorItem, List<CurrencyItemResponse>>

    suspend fun getSymbolTexts(): Either<ErrorItem, Map<String, String>>

}