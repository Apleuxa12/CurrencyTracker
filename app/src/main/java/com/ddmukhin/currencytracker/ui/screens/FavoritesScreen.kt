package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.FavoritesScreenSpec
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec
import com.ddmukhin.currencytracker.viewmodel.FavoritesViewModel
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel
import com.ddmukhin.currencytracker.viewmodel.SortViewModel
import timber.log.Timber

@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    globalViewModel: GlobalViewModel = hiltViewModel(),
    sortViewModel: SortViewModel = hiltViewModel()
) {
    LazyColumn{
        item{
            GlobalCurrencyScreen(navController, globalViewModel)
        }
    }
}