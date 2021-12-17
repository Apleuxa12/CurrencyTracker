package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currenciesConverter: CurrenciesConverter
) : CurrencyRepository {
    override suspend fun getLatestCurrencies(
        base: String,
        symbols: List<String>
    ): List<CurrencyItem> {
        return currenciesConverter.latestToUi(currencyService.latest(base, symbols))
    }

    override suspend fun getSymbolText(base: String): String {
        return currenciesConverter.symbolToText(base, currencyService.symbols())
    }
}