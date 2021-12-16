package com.ddmukhin.currencytracker.navigation.spec

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.screens.FavoritesScreen

object FavoritesScreenSpec : ScreenSpec {
    override val route = "favorites"

    @Composable
    override fun Content(navController: NavController, navBackStackEntry: NavBackStackEntry) {
        FavoritesScreen(navController)
    }
}