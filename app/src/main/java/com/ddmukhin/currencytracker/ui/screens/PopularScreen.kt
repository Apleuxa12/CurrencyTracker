package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.FavoritesScreenSpec
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

@Composable
fun PopularScreen(navController: NavController) {
    Text(
        text = "POPULAR",
        modifier = Modifier.clickable { navController.navigate(FavoritesScreenSpec.route) },
        style = MaterialTheme.typography.h2
    )

    val list = listOf<CurrencyItem>()

    list.sortedWith { p0, p1 -> TODO("Not yet implemented") }
}