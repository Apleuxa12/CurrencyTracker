package com.ddmukhin.currencytracker.converter

import arrow.core.Either
import com.ddmukhin.currencytracker.data.remote.model.response.CurrencyItemResponse
import com.ddmukhin.currencytracker.data.remote.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.remote.model.response.SymbolsResponse
import com.ddmukhin.currencytracker.data.persistence.model.Currency
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import retrofit2.Response

object CurrenciesConverterImpl : CurrenciesConverter {
    override fun latestToUi(response: Response<LatestCurrenciesResponse>): Either<BaseError, List<CurrencyItemResponse>> {
        if (!response.isSuccessful || response.body() == null)
            return Either.Left(BaseError())

        val body = response.body()!!

        if (body.error != null)
            return Either.Left(body.error)

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

    override fun symbolsToMap(response: Response<SymbolsResponse>): Either<BaseError, Map<String, String>> {
        if (!response.isSuccessful || response.body() == null)
            return Either.Left(BaseError())

        val body = response.body()!!

        if (body.error != null)
            return Either.Left(body.error)

        if(body.symbols == null)
            return Either.Left(BaseError())

        return Either.Right(body.symbols)
    }

    override fun uiToDatabase(item: CurrencyItem) = Currency(item.base, item.name)

    override fun databaseToUi(currency: Currency) =
        CurrencyItem(currency.name, currency.base, isFavorite = true)
}