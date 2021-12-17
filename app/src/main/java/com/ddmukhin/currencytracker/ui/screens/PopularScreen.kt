package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.FavoritesScreenSpec
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.model.SortSubItem
import com.ddmukhin.currencytracker.ui.model.SortSubType
import com.ddmukhin.currencytracker.ui.model.SortType
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel
import com.ddmukhin.currencytracker.viewmodel.SortViewModel
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState

@Composable
fun PopularScreen(
    navController: NavController,
    currentViewModel: PopularViewModel = hiltViewModel(),
    sortViewModel: SortViewModel = hiltViewModel()
) {

}