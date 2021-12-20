package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.state.GlobalCurrencyState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GlobalViewModel : ViewModel(), ViewModelContract<GlobalCurrencyState> {

    private val _state = MutableStateFlow(
        GlobalCurrencyState(
            null,
            emptyList()
        )
    )

    override val state: StateFlow<GlobalCurrencyState> = _state.asStateFlow()

    fun updateGlobalCurrencyItem(globalCurrency: CurrencyItem?) {
        _state.value = _state.value.copy(globalCurrency = globalCurrency)
    }
}