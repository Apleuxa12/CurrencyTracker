package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.SortSubItem
import com.ddmukhin.currencytracker.viewmodel.SortViewModel
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

@Composable
fun SortScreen(
    navController: NavController,
    sortViewModel: SortViewModel = hiltViewModel()
) {
    val sortItems by sortViewModel.state.collectAsState()

    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Сортировка",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 6.dp),
            textAlign = TextAlign.Center
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(sortItems.list) {
            Text(text = it.name, style = MaterialTheme.typography.h4)
            Column {
                SortItemScreen(sortItem = it) { ids ->
                    sortViewModel.selectSort(ids)
                }
            }
            Spacer(Modifier.width(20.dp))
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { sortViewModel.resetSort() }) {
                Text(text = "Сбросить", style = MaterialTheme.typography.h5)
            }
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Применить", style = MaterialTheme.typography.h5)
            }
        }
    }
}

@Composable
fun <T : Comparable<T>> SortItemScreen(
    sortItem: SortItem<T>,
    selectSort: (Pair<Int, Int>) -> Unit
) {
    sortItem.subItems.forEach {
        SortSubItemScreen(
            sortItem = sortItem,
            sortSubItem = it,
            selectSort = selectSort,
            modifier = Modifier.padding(start = 8.dp)
        )
        Divider()
    }
}

@Composable
fun <T : Comparable<T>> SortSubItemScreen(
    sortItem: SortItem<T>,
    sortSubItem: SortSubItem<T>,
    selectSort: (Pair<Int, Int>) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { selectSort(sortItem.id to sortSubItem.id) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = sortSubItem.isSelected,
            onCheckedChange = {
                selectSort(sortItem.id to sortSubItem.id)
            }
        )
        Text(
            text = sortSubItem.name,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h5
        )
    }
}