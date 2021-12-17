package com.ddmukhin.currencytracker.navigation.spec

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.ui.screens.SortScreen

object SortScreenSpec : ScreenSpec {
    override val route = "sort"

    @Composable
    override fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        SortScreen(navController)
    }
}