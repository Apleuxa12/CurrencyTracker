package com.ddmukhin.currencytracker.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ddmukhin.currencytracker.navigation.spec.ScreenSpec
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController, startDestinationSpec: ScreenSpec) {
    NavHost(navController, startDestination = startDestinationSpec.route){
        ScreenSpec.screens.forEach { spec ->
            composable(
                spec.route,
                spec.arguments,
                spec.deepLinks
            ){
                spec.Content(navController, it)
            }
        }
    }
}