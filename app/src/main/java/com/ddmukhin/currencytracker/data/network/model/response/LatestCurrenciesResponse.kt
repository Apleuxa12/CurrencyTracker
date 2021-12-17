package com.ddmukhin.currencytracker.data.network.model.response

import com.ddmukhin.currencytracker.data.network.model.response.base.BaseError
import com.ddmukhin.currencytracker.data.network.model.response.base.BaseSuccess

data class LatestCurrenciesResponse(

    override val success: Boolean,

    val error: BaseError,

    val timestamp: Int,

    val base: String,

    val date: String,

    val values: Map<String, Double>

) : BaseSuccess
