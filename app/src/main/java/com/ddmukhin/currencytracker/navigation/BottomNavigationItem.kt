package com.ddmukhin.currencytracker.navigation

sealed class BottomNavigationItem(val route: String, val title: String, val seleted: Boolean) {

    object Popular : BottomNavigationItem("popular", "Популярные", true)
    object Favorites : BottomNavigationItem("favorites", "Избрынные", false)

}