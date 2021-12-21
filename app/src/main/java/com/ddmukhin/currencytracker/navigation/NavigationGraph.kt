package com.ddmukhin.currencytracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ddmukhin.currencytracker.navigation.spec.FavoritesScreenSpec
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec
import com.ddmukhin.currencytracker.navigation.spec.ScreenSpec
import com.ddmukhin.currencytracker.navigation.spec.SortScreenSpec
import com.ddmukhin.currencytracker.ui.screens.FavoritesScreen
import com.ddmukhin.currencytracker.ui.screens.PopularScreen
import com.ddmukhin.currencytracker.ui.screens.SortScreen
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel

@Composable
fun NavigationGraph(navController: NavHostController, startDestinationSpec: ScreenSpec) {
    val globalViewModel: GlobalViewModel = hiltViewModel()

    NavHost(navController, startDestination = startDestinationSpec.route) {
        composable(PopularScreenSpec.route){
            PopularScreen(navController, globalViewModel = globalViewModel)
        }

        composable(FavoritesScreenSpec.route){
            FavoritesScreen(navController, globalViewModel = globalViewModel)
        }

        composable(SortScreenSpec.route){
            SortScreen(navController)
        }
    }
}