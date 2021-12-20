package com.ddmukhin.currencytracker.ui.model

data class CurrencyItem(
    val name: String,
    val base: String,
    var value: Double,
    var isFavorite: Boolean = false
)
