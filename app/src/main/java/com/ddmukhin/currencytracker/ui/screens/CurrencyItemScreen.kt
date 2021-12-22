package com.ddmukhin.currencytracker.ui.screens

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ddmukhin.currencytracker.R
import com.ddmukhin.currencytracker.ui.model.CurrencyItem

@Composable
fun CurrencyItemScreen(
    item: CurrencyItem,
    onFavoriteClick: (CurrencyItem) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.h5
) {
    val valueText = if (item.value > 0.0) "%.2f".format(item.value) else ""

    val itemText = "${if (item.name.length + valueText.length < 30) item.name else item.base} $valueText"

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        ScalableText(
            text = itemText,
            step = 0.1,
            modifier = Modifier.wrapContentWidth()
        )

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(), contentAlignment = Alignment.CenterEnd
        ) {
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

@Composable
fun ScalableText(
    modifier: Modifier = Modifier,
    text: String,
    initialTextStyle: TextStyle = MaterialTheme.typography.h6,
    step: Double
) {
    if (step >= 1.0)
        return

    var textStyle by remember { mutableStateOf(initialTextStyle) }
    var readyToDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        style = textStyle,
        maxLines = 1,
        softWrap = true,
        modifier = Modifier.drawWithContent {
            if (readyToDraw) drawContent()
        },
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth) {
                textStyle = textStyle.copy(fontSize = textStyle.fontSize * (1 - step))
            } else
                readyToDraw = true
        }
    )
}