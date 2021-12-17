package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.SortSubItem
import com.ddmukhin.currencytracker.ui.model.SortSubType
import com.ddmukhin.currencytracker.ui.model.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SortViewModel(
    list: List<SortItem<*>> = listOf(
        SortItem(
            id = 0,
            name = "по алфавиту",
            type = SortType.Alphabet,
            subItems = listOf(
                SortSubItem(
                    0,
                    name = "по убыванию",
                    type = SortSubType.Up()
                ),
                SortSubItem(
                    1,
                    name = "по возрастанию",
                    type = SortSubType.Down()
                )
            )
        ),
        SortItem(
            id = 1,
            name = "по значению",
            type = SortType.Value,
            subItems = listOf(
                SortSubItem(
                    0,
                    name = "по убыванию",
                    type = SortSubType.Up()
                ),
                SortSubItem(
                    1,
                    name = "по возрастанию",
                    type = SortSubType.Down()
                )
            )
        )
    )
) : ViewModel(), ViewModelContract<List<SortItem<*>>> {

    private val _state = MutableStateFlow(list)

    override val state: StateFlow<List<SortItem<*>>> = _state.asStateFlow()

    fun selectSort(id: Pair<Int, Int>){
        val current = _state.value

        current.forEach { filter ->
            filter.subItems.forEach {
                it.isSelected = false
            }
        }

        current.filter { it.id == id.first }.forEach { filter ->
            filter.subItems.filter { it.id == id.second }.forEach {
                it.isSelected = true
            }
        }

        _state.value = current
    }
}