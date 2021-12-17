package com.ddmukhin.currencytracker.data.network

import com.ddmukhin.currencytracker.data.network.model.response.LatestCurrenciesResponse
import com.ddmukhin.currencytracker.data.network.model.response.SymbolsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("/v1/latest")
    suspend fun latest(
        @Query("base") base: String,
        @Query("symbols") symbols: List<String>
    ): Response<LatestCurrenciesResponse>

    @GET("/v1/symbols")
    suspend fun symbols(): Response<SymbolsResponse>
}