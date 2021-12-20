package com.ddmukhin.currencytracker.converter

import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.remote.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.ui.model.ErrorItem
import retrofit2.Response

interface CurrenciesConverter {

    fun latestToUi(response: Response<LatestCurrenciesResponse>): Either<ErrorItem, List<CurrencyItemResponse>>

    fun symbolsToMap(response: Response<SymbolsResponse>) : Either<ErrorItem, Map<String, String>>

    fun uiToDatabase(item: CurrencyItem): Currency

    fun databaseToUi(currency: Currency): CurrencyItem
}