package com.ddmukhin.currencytracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.SortSubItem
import com.ddmukhin.currencytracker.ui.model.SortSubType
import com.ddmukhin.currencytracker.ui.model.SortType
import com.ddmukhin.currencytracker.viewmodel.state.SortState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor() : ViewModel(), ViewModelContract<SortState> {

    private val sortItems = listOf(
        SortItem(
            id = 0,
            name = "по алфавиту",
            type = SortType.Alphabet,
            subItems = listOf(
                SortSubItem(
                    0,
                    name = "по убыванию",
                    type = SortSubType.Down()
                ),
                SortSubItem(
                    1,
                    name = "по возрастанию",
                    type = SortSubType.Up()
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
                    type = SortSubType.Down()
                ),
                SortSubItem(
                    1,
                    name = "по возрастанию",
                    type = SortSubType.Up()
                )
            )
        )
    )

    private val _state = MutableStateFlow(SortState(sortItems))

    override val state: StateFlow<SortState> = _state.asStateFlow()

    fun resetSort() {
        _state.value = SortState(
            listOf(
                SortItem(
                    id = 0,
                    name = "по алфавиту",
                    type = SortType.Alphabet,
                    subItems = listOf(
                        SortSubItem(
                            0,
                            name = "по убыванию",
                            type = SortSubType.Down()
                        ),
                        SortSubItem(
                            1,
                            name = "по возрастанию",
                            type = SortSubType.Up()
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
                            type = SortSubType.Down()
                        ),
                        SortSubItem(
                            1,
                            name = "по возрастанию",
                            type = SortSubType.Up()
                        )
                    )
                )
            )
        )
    }

    fun selectSort(id: Pair<Int, Int>) {
        _state.value = SortState(
            listOf(
                SortItem(
                    id = 0,
                    name = "по алфавиту",
                    type = SortType.Alphabet,
                    subItems = listOf(
                        SortSubItem(
                            0,
                            name = "по убыванию",
                            type = SortSubType.Down()
                        ),
                        SortSubItem(
                            1,
                            name = "по возрастанию",
                            type = SortSubType.Up()
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
                            type = SortSubType.Down()
                        ),
                        SortSubItem(
                            1,
                            name = "по возрастанию",
                            type = SortSubType.Up()
                        )
                    )
                )
            ).apply {
                find { it.id == id.first }?.subItems?.find { it.id == id.second }?.isSelected = true
            }
        )
    }

    fun map(state: SortState): List<Boolean> {
        val list = mutableListOf<Boolean>()

        state.list.forEach { sortItem ->
            sortItem.subItems.forEach { subItem ->
                list.add(subItem.isSelected)
            }
        }

        return list
    }
}