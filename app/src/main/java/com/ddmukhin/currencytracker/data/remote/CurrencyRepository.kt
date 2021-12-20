package com.ddmukhin.currencytracker.data.remote

import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError

interface CurrencyRepository {

    suspend fun getLatestCurrencies(base: String, symbols: List<String> = listOf()): Either<BaseError, List<CurrencyItemResponse>>

    suspend fun getSymbolTexts(): Either<BaseError, Map<String, String>>

}