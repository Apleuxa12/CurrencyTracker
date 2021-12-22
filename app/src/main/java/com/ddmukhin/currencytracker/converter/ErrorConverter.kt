package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError
import com.ddmukhin.currencytracker.ui.model.ErrorItem

interface ErrorConverter {

    fun mapRemoteException(e: Exception): BaseError

    fun baseErrorToUi(error: BaseError): ErrorItem

    fun mapRemoteExceptionToUi(e: Exception) = baseErrorToUi(mapRemoteException(e))

    fun errorStringBodyToBaseError(errorBody: String): BaseError
}