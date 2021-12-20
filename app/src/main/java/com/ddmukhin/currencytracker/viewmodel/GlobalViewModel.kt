package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.const
import arrow.core.flatMap
import com.ddmukhin.currencytracker.data.remote.CurrencyRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.state.GlobalCurrencyState
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GlobalViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel(), ViewModelContract<GlobalCurrencyState> {

    private val _state = MutableStateFlow(
        GlobalCurrencyState(
            CurrencyItem(name = "Euro", base = "EUR"),
            emptyList()
        )
    )

    override val state: StateFlow<GlobalCurrencyState> = _state.asStateFlow()

    init{
        loadInitialCurrencies()
    }

    fun updateGlobalCurrencyItem(globalCurrency: CurrencyItem) {
        _state.value = _state.value.copy(globalCurrency = globalCurrency)
    }

    private fun loadInitialCurrencies(){
        viewModelScope.launch {
            val item = _state.value.globalCurrency

            val response = currencyRepository.getLatestCurrencies(
                base = item.base
            )

            val textsResponse = currencyRepository.getSymbolTexts()

            val mappedResponse = textsResponse.flatMap { map ->
                response.map {
                    it.map { response ->
                        CurrencyItem(
                            name = map[response.base] ?: response.base,
                            base = response.base,
                            value = response.value
                        )
                    }
                }
            }

            when(mappedResponse){
                is Either.Left -> {
//                    do nothing
                }

                is Either.Right -> {
                    _state.value = GlobalCurrencyState(
                        item,
                        mappedResponse.value
                    )
                }
            }
        }
    }
}