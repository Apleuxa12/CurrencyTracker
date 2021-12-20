package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.network.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.network.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.network.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

interface CurrenciesConverter {

    fun latestToUi(response: Response<LatestCurrenciesResponse>): List<CurrencyItemResponse>?

    fun symbolsToMap(response: Response<SymbolsResponse>) : Map<String, String>?

    fun uiToDatabase(item: CurrencyItem): Currency

    fun databaseToUi(currency: Currency): CurrencyItem
}