package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel(), ViewModelContract<PopularCurrencyState> {
    private val _state = MutableStateFlow<PopularCurrencyState>(PopularCurrencyState.Loading)

    override val state: StateFlow<PopularCurrencyState> = _state.asStateFlow()

    fun <T : Comparable<T>> applySort(sortItem: SortItem<T>){

    }

    fun setGlobalCurrency(currencyItem: CurrencyItem){
        _state.value = PopularCurrencyState.Loading

        val textsResponse = viewModelScope.async {
            currencyRepository.getSymbolTexts()
        }

        viewModelScope.launch {
            val response = currencyRepository.getLatestCurrencies(
                base = currencyItem.name
            )

            if(response == null){
                _state.value = PopularCurrencyState.Error("Ошибка")
            }else{
                val result = mutableListOf<CurrencyItem>()

                val texts = textsResponse.await()

                response.forEach {
                    result.add(
                        CurrencyItem(
                            name = texts?.get(it.base) ?: "",
                            value = it.value
                        )
                    )
                }
                result.updateFavorites()

                _state.value = PopularCurrencyState.Success(
                    currentCurrencyItem = currencyItem,
                    popularCurrencies = result
                )
            }
        }

    }

    private fun List<CurrencyItem>.updateFavorites(){

    }

}