package com.ddmukhin.currencytracker.navigation.spec

import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.ddmukhin.currencytracker.ui.model.SortItem
import com.ddmukhin.currencytracker.ui.screens.PopularScreen
import com.ddmukhin.currencytracker.utils.fromJson
import com.ddmukhin.currencytracker.utils.toJson

object PopularScreenSpec : ScreenSpec {
    override val route = "popular"
}