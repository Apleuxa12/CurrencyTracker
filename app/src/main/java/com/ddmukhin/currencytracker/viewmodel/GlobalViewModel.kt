package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GlobalViewModel : ViewModel(), ViewModelContract<CurrencyItem?> {

    private val _state = MutableStateFlow<CurrencyItem?>(null)

    override val state: StateFlow<CurrencyItem?> = _state.asStateFlow()

    fun updateGlobalCurrencyItem(item: CurrencyItem){
        _state.value = item
    }
}