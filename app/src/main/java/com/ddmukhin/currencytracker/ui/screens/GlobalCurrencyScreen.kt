package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.R
import com.ddmukhin.currencytracker.navigation.navigate
import com.ddmukhin.currencytracker.navigation.spec.SortScreenSpec
import com.ddmukhin.currencytracker.ui.model.CurrencyItem
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import timber.log.Timber

@Composable
fun GlobalCurrencyScreen(
    navController: NavController,
    globalViewModel: GlobalViewModel
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(Modifier.weight(1f))
        DropDownCurrency(globalViewModel, modifier = Modifier.weight(8f))
        Spacer(Modifier.weight(1f))
        SortButton(
            navController, modifier = Modifier
                .weight(2f)
        )
        Spacer(Modifier.weight(1f))
    }
}

@Composable
fun DropDownCurrency(globalViewModel: GlobalViewModel, modifier: Modifier = Modifier) {
    val state by globalViewModel.state.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopStart)
        ) {
            Text(
                text = "Текущая валюта : " + state.currencies[state.selectedIndex].name,
                modifier = Modifier
                    .fillMaxSize()
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
    }
}

@Composable
fun SortButton(navController: NavController, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = {
            navController.navigate(SortScreenSpec)
        },
        shape = CircleShape,
        border = BorderStroke(3.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            backgroundColor = Color.White
        ),
        modifier = modifier.size(50.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sort),
            contentDescription = "sort",
            modifier = Modifier.fillMaxSize()
        )
    }
}