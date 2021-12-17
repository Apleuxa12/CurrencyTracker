package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.network.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.network.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

object CurrenciesConverterImpl : CurrenciesConverter {
    override fun latestToUi(response: Response<LatestCurrenciesResponse>): List<CurrencyItem> {
        TODO("Not yet implemented")
    }

    override fun symbolToText(base: String, symbol: Response<SymbolsResponse>): String {
        TODO("Not yet implemented")
    }
}