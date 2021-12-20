package com.ddmukhin.currencytracker.converter.impl

import com.ddmukhin.currencytracker.converter.ErrorConverter
import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.ErrorItem
import okio.IOException
import retrofit2.HttpException

object ErrorConverterImpl : ErrorConverter {

    override fun mapRemoteException(e: Exception) = when (e) {
        is IOException -> BaseError(info = "No internet connection")
        is HttpException -> BaseError(code = e.code().toString(), info = e.message())
        else -> BaseError(info = "Unexpected exception")
    }

    override fun baseErrorToUi(error: BaseError): ErrorItem {
        var msg = error.info ?: "Unexpected error"

        error.code?.let {
            msg += " ($it)"
        }

        return ErrorItem(msg)
    }

}