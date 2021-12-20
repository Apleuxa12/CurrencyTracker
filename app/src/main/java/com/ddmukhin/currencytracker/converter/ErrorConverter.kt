package com.ddmukhin.currencytracker.converter

import com.ddmukhin.currencytracker.data.remote.model.response.base.BaseError

interface ErrorConverter {

    fun mapRemoteException(e: Exception): BaseError

}