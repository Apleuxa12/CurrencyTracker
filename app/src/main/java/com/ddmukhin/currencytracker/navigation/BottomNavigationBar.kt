package com.ddmukhin.currencytracker.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.ddmukhin.currencytracker.R

@Composable
fun BottomNavigationBar() {
    val items = listOf(
        BottomNavigationItem.Popular,
        BottomNavigationItem.Favorites
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { },
                label = { Text(text = item.title) },
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = item.seleted,
                onClick = {

                })
        }
    }
}