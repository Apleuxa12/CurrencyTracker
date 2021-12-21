package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.ddmukhin.currencytracker.R
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

@Composable
fun CurrencyItemScreen(
    item: CurrencyItem,
    onFavoriteClick: (CurrencyItem) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(text = item.name)
        Text(text = item.value.toString())

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            if (!item.isFavorite)
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "not favorite",
                    modifier = Modifier.clickable { onFavoriteClick(item) }
                )
            else
                Icon(
                    painter = painterResource(R.drawable.ic_check),
                    contentDescription = "favorite",
                    modifier = Modifier.clickable { onFavoriteClick(item) }
                )
        }
    }
}