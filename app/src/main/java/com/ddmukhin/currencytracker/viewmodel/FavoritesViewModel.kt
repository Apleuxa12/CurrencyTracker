package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.data.network.CurrencyRepository
import com.ddmukhin.currencytracker.data.persistence.PersistenceRepository
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    currencyRepository: CurrencyRepository,
    persistenceRepository: PersistenceRepository
) : ViewModel(), ViewModelContract<FavoritesCurrencyState> {

    private val _state = MutableStateFlow<FavoritesCurrencyState>(FavoritesCurrencyState.Loading)

    override val state: StateFlow<FavoritesCurrencyState> = _state.asStateFlow()

    fun loadCurrencies(){

    }

    fun updateValues(globalCurrency: CurrencyItem){
        
    }
}