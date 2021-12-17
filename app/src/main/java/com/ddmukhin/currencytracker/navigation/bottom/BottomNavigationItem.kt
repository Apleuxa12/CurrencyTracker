package com.ddmukhin.currencytracker.navigation.bottom

import com.ddmukhin.currencytracker.navigation.spec.FavoritesScreenSpec
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec

sealed class BottomNavigationItem(val route: String, val title: String) {

    object Popular : BottomNavigationItem(PopularScreenSpec.route, "Популярные")
    object Favorites : BottomNavigationItem(FavoritesScreenSpec.route, "Избранное")

}