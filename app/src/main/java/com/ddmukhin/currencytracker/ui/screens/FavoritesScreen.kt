package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec

@Composable
fun FavoritesScreen(navController: NavController) {
    Text(
        text = "FAVORITES",
        modifier = Modifier.clickable { navController.navigate(PopularScreenSpec.route) },
        style = MaterialTheme.typography.h2
    )
}