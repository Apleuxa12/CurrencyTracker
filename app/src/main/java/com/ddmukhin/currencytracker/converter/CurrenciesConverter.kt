package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.network.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.network.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

interface CurrenciesConverter {

    fun latestToUi(response: Response<LatestCurrenciesResponse>): List<CurrencyItem>?

    fun symbolToText(base: String, response: Response<SymbolsResponse>) : String?

}