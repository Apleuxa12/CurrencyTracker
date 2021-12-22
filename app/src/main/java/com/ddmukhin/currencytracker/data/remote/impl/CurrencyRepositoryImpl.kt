package com.ddmukhin.currencytracker.data.remote.impl

import arrow.core.Either
import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.converter.ErrorConverter
import com.ddmukhin.currencytracker.data.remote.CurrencyRepository
import com.ddmukhin.currencytracker.data.remote.CurrencyService
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.ErrorItem

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currenciesConverter: CurrenciesConverter,
    private val errorConverter: ErrorConverter
) : CurrencyRepository {
    override suspend fun getLatestCurrencies(
        base: String,
        symbols: String
    ): Either<ErrorItem, List<CurrencyItemResponse>> {
        return try {
            currenciesConverter.latestToUi(currencyService.latest(base, symbols))
        } catch (e: Exception) {
            Either.Left(errorConverter.mapRemoteExceptionToUi(e))
        }
    }

    override suspend fun getSymbolTexts(): Either<ErrorItem, Map<String, String>> {
        return try {
            currenciesConverter.symbolsToMap(currencyService.symbols())
        } catch (e: Exception) {
            Either.Left(errorConverter.mapRemoteExceptionToUi(e))
        }
    }


}