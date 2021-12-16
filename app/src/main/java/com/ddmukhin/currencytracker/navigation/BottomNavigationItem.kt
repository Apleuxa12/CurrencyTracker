package com.ddmukhin.currencytracker.navigation

sealed class BottomNavigationItem(val route: String, val title: String) {

    object Popular : BottomNavigationItem("popular", "Популярные")
    object Favorites : BottomNavigationItem("favorites", "Избрынные")

}