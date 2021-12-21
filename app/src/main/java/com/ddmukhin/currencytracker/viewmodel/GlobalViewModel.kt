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

    companion object {
        const val MAX_SIZE = 10
    }

    private val baseCurrency = CurrencyItem(name = "Euro", base = "EUR")

    private val _state = MutableStateFlow(
        GlobalCurrencyState(
            0,
            listOf(
                baseCurrency
            )
        )
    )

    override val state: StateFlow<GlobalCurrencyState> = _state.asStateFlow()

    init {
        loadInitialCurrencies()
    }

    fun updateGlobalCurrencyIndex(index: Int) {
        _state.value = _state.value.copy(selectedIndex = index)
    }

    fun loadInitialCurrencies() {
        viewModelScope.launch {
            val response = currencyRepository.getLatestCurrencies()

            val textsResponse = currencyRepository.getSymbolTexts()

            val mappedResponse = textsResponse.flatMap { map ->
                response.map {
                    it.map { response ->
                        CurrencyItem(
                            name = map[response.base] ?: response.base,
                            base = response.base
                        )
                    }
                }
            }

            when (mappedResponse) {
                is Either.Left -> {
//                    do nothing
                }

                is Either.Right -> {
                    val values =
                        mappedResponse.value.filter { it.base != baseCurrency.base }.shuffled()
                            .take(MAX_SIZE - 1)

                    _state.value = GlobalCurrencyState(
                        _state.value.selectedIndex,
                        listOf(baseCurrency) + values
                    )
                }
            }
        }
    }
}