package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.network.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.network.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.network.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

object CurrenciesConverterImpl : CurrenciesConverter {
    override fun latestToUi(response: Response<LatestCurrenciesResponse>): List<CurrencyItemResponse>? {
        if(!response.isSuccessful || response.body() == null)
            return null
        val body = response.body()!!

        if(!body.success)
            return null

        if(body.values == null || body.values.isEmpty())
            return emptyList()

        val result = mutableListOf<CurrencyItemResponse>()

        body.values.forEach {
            result.add(CurrencyItemResponse(
                base = it.key,
                value = it.value
            ))
        }

        return result
    }

    override fun symbolsToMap(response: Response<SymbolsResponse>): Map<String, String>? {
        if(!response.isSuccessful || response.body() == null)
            return null
        val body = response.body()!!

        if(!body.success || body.symbols == null || body.symbols.isEmpty())
            return null

        return body.symbols
    }
}