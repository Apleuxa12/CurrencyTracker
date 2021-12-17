package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.viewmodel.state.SortState
import kotlinx.coroutines.flow.StateFlow

class SortViewModel : ViewModel(), ViewModelContract<SortState> {
    override val state: StateFlow<SortState>
        get() = TODO("Not yet implemented")
}