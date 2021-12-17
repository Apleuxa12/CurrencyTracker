package com.ddmukhin.currencytracker.data.network.model.request

data class LatestCurrenciesRequest(

    val base: String,

    val symbols: List<String> = listOf()

)
