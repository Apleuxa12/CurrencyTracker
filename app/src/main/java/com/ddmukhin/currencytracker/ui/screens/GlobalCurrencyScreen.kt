package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel

@Composable
fun GlobalCurrencyScreen(
    globalViewModel: GlobalViewModel = hiltViewModel()
) {
    val state by globalViewModel.state.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    globalViewModel.loadInitialCurrencies()

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopStart)) {
        Text(text = state.globalCurrency.name, modifier = Modifier.clickable { expanded = true })

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            state.currencies.forEach {
                DropdownMenuItem(onClick = {
                    globalViewModel.updateGlobalCurrencyItem(it)
                    expanded = false
                }) {
                    Text(text = it.name)
                }
            }
        }
    }
}