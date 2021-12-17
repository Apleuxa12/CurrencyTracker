package com.ddmukhin.currencytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.ddmukhin.currencytracker.navigation.bottom.BottomNavigationBar
import com.ddmukhin.currencytracker.navigation.NavigationGraph
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec
import com.ddmukhin.currencytracker.viewmodel.FavoritesViewModel
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) {
                NavigationGraph(navController, startDestinationSpec = PopularScreenSpec)
            }
        }
    }
}