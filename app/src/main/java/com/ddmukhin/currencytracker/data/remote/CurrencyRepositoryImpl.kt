package com.ddmukhin.currencytracker.data.remote

import arrow.core.Either
import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.converter.ErrorConverter
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError

class CurrencyRepositoryImpl(
    private val currencyService: CurrencyService,
    private val currenciesConverter: CurrenciesConverter,
    private val errorConverter: ErrorConverter
) : CurrencyRepository {
    override suspend fun getLatestCurrencies(
        base: String,
        symbols: List<String>
    ): Either<BaseError, List<CurrencyItemResponse>> {
        return try {
            currenciesConverter.latestToUi(currencyService.latest(base, symbols))
        } catch (e: Exception) {
            Either.Left(errorConverter.mapRemoteException(e))
        }
    }

    override suspend fun getSymbolTexts(): Either<BaseError, Map<String, String>> {
        return try {
            currenciesConverter.symbolsToMap(currencyService.symbols())
        } catch (e: Exception) {
            Either.Left(errorConverter.mapRemoteException(e))
        }
    }


}