package com.ddmukhin.currencytracker.navigation

import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.ScreenSpec

fun NavController.navigate(screenSpec: ScreenSpec){
    navigate(screenSpec.route) {

        graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }

        launchSingleTop = true
        restoreState = true
    }
}