package com.ddmukhin.currencytracker.converter

import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.remote.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

interface CurrenciesConverter {

    fun latestToUi(response: Response<LatestCurrenciesResponse>): Either<BaseError, List<CurrencyItemResponse>>

    fun symbolsToMap(response: Response<SymbolsResponse>) : Either<BaseError, Map<String, String>>

    fun uiToDatabase(item: CurrencyItem): Currency

    fun databaseToUi(currency: Currency): CurrencyItem
}