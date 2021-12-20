package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import okio.IOException
import retrofit2.HttpException

object ErrorConverterImpl : ErrorConverter {

    override fun mapRemoteException(e: Exception) = when (e) {
        is IOException -> BaseError(info = "No internet connection")
        is HttpException -> BaseError(code = e.code().toString(), info = e.message())
        else -> BaseError(info = "Unexpected exception")
    }

}