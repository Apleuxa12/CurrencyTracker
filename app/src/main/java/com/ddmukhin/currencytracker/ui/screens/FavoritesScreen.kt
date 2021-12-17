package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec
import com.ddmukhin.currencytracker.viewmodel.FavoritesViewModel
import com.ddmukhin.currencytracker.viewmodel.SortViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    currentViewModel: FavoritesViewModel = hiltViewModel(),
    sortViewModel: SortViewModel = hiltViewModel()
) {

}