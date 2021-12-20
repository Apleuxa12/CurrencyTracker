package com.ddmukhin.currencytracker.utils

import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState
import kotlinx.coroutines.flow.StateFlow

inline fun <reified T> StateFlow<*>.getStateAs() =
    if (this.value !is T) null else this.value as T