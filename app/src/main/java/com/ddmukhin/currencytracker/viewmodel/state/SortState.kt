package com.ddmukhin.currencytracker.viewmodel.state

import com.ddmukhin.currencytracker.ui.model.SortItem

data class SortState(val list: List<SortItem<*>>)