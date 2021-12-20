package com.ddmukhin.currencytracker.converter.impl

import arrow.core.Either
import com.ddmukhin.currencytracker.converter.CurrenciesConverter
import com.ddmukhin.currencytracker.converter.ErrorConverter
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.remote.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.ui.model.ErrorItem
import retrofit2.Response

class CurrenciesConverterImpl(
    private val errorConverter: ErrorConverter
) : CurrenciesConverter {
    override fun latestToUi(response: Response<LatestCurrenciesResponse>): Either<ErrorItem, List<CurrencyItemResponse>> {
        if (!response.isSuccessful || response.body() == null)
            return Either.Left(errorConverter.baseErrorToUi(BaseError()))

        val body = response.body()!!

        if (body.error != null)
            return Either.Left(errorConverter.baseErrorToUi(body.error))

        if (!body.success || body.values == null || body.values.isEmpty())
            return Either.Right(emptyList())

        val result = mutableListOf<CurrencyItemResponse>()

        body.values.filter { it.key != null && it.value != null }.forEach {
            result.add(
                CurrencyItemResponse(
                    base = it.key!!,
                    value = it.value!!
                )
            )
        }

        return Either.Right(result)
    }

    override fun symbolsToMap(response: Response<SymbolsResponse>): Either<ErrorItem, Map<String, String>> {
        if (!response.isSuccessful || response.body() == null)
            return Either.Left(errorConverter.baseErrorToUi(BaseError()))

        val body = response.body()!!

        if (body.error != null)
            return Either.Left(errorConverter.baseErrorToUi(body.error))

        if(body.symbols == null)
            return Either.Left(errorConverter.baseErrorToUi(BaseError()))

        return Either.Right(body.symbols)
    }

    override fun uiToDatabase(item: CurrencyItem) = Currency(item.base, item.name)

    override fun databaseToUi(currency: Currency) =
        CurrencyItem(currency.name, currency.base, isFavorite = true)
}