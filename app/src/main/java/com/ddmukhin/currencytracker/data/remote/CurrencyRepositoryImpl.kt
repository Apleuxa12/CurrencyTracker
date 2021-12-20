package com.ddmukhin.currencytracker.data.remote

import arrow.core.Either
import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currenciesConverter: CurrenciesConverter
) : CurrencyRepository {
    override suspend fun getLatestCurrencies(
        base: String,
        symbols: List<String>
    ): Either<BaseError, List<CurrencyItemResponse>> {
        return currenciesConverter.latestToUi(currencyService.latest(base, symbols))
    }

    override suspend fun getSymbolTexts(): Either<BaseError, Map<String, String>> {
        return currenciesConverter.symbolsToMap(currencyService.symbols())
    }


}