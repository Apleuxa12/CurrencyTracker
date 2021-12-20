package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.network.model.response.CurrencyItemResponse

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currenciesConverter: CurrenciesConverter
) : CurrencyRepository {
    override suspend fun getLatestCurrencies(
        base: String,
        symbols: List<String>
    ): List<CurrencyItemResponse>? {
        return currenciesConverter.latestToUi(currencyService.latest(base, symbols))
    }

    override suspend fun getSymbolTexts(): Map<String, String>? {
        return currenciesConverter.symbolsToMap(currencyService.symbols())
    }


}