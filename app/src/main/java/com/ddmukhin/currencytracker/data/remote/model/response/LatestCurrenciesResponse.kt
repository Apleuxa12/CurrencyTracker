package com.ddmukhin.currencytracker.data.remote.model.response

import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseSuccess
import com.google.gson.annotations.SerializedName

data class LatestCurrenciesResponse(

    override val success: Boolean,

    val timestamp: Int?,

    val base: String?,

    val date: String?,

    @field:SerializedName("rates")
    val values: Map<String?, Double?>?

) : BaseSuccess
