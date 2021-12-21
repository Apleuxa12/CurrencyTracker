package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import timber.log.Timber

@Composable
fun GlobalCurrencyScreen(
    globalViewModel: GlobalViewModel
) {
    val state by globalViewModel.state.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Text(
                text = "Текущая валюта : " + state.currencies[state.selectedIndex].name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                    },
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                state.currencies.forEachIndexed { index, it ->
                    DropdownMenuItem(onClick = {
                        globalViewModel.updateGlobalCurrencyIndex(index)
                        expanded = false
                    }) {
                        Text(text = it.name, style = MaterialTheme.typography.h5)
                    }
                    Divider()
                }
            }
        }
        Divider()
    }
}