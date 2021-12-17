package com.ddmukhin.currencytracker.data.network.model.response

import com.ddmukhin.currencytracker.data.network.model.response.base.BaseSuccess

data class SymbolsResponse(

    override val success: Boolean,

    val symbols: Map<String, String>

) : BaseSuccess