package com.ddmukhin.currencytracker.data.remote.model.request

data class LatestCurrenciesRequest(

    val base: String,

    val symbols: List<String> = listOf()

)
