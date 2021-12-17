package com.ddmukhin.currencytracker.viewmodel

import kotlinx.coroutines.flow.StateFlow

interface ViewModelContract<T> {

    val state: StateFlow<T>

}