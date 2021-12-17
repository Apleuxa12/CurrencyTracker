package com.ddmukhin.currencytracker.navigation.spec

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.ui.screens.PopularScreen
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel

object PopularScreenSpec : ScreenSpec {
    override val route = "popular"

    @Composable
    override fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        PopularScreen(navController)
    }
}