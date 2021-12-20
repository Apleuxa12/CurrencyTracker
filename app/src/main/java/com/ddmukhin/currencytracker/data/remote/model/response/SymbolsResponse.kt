package com.ddmukhin.currencytracker.data.remote.model.response

import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseSuccess

data class SymbolsResponse(

    override val success: Boolean,

    val error: BaseError?,

    val symbols: Map<String, String>?

) : BaseSuccess