package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel
import com.ddmukhin.currencytracker.viewmodel.SortViewModel
import com.ddmukhin.currencytracker.viewmodel.state.PopularCurrencyState

@Composable
fun PopularScreen(
    navController: NavController,
    popularViewModel: PopularViewModel = hiltViewModel(),
    globalViewModel: GlobalViewModel = hiltViewModel(),
    sortViewModel: SortViewModel = hiltViewModel()
) {
    val popular by popularViewModel.state.collectAsState()
    val globalCurrency by globalViewModel.state.collectAsState()
    val sort by sortViewModel.state.collectAsState()

    popularViewModel.updateWithGlobalCurrencyItem(
        currencyItem = globalCurrency.currencies[globalCurrency.selectedIndex],
        sort = sort.list
    )

    LazyColumn {
        item {
            GlobalCurrencyScreen(navController, globalViewModel)
        }

        when (val state = popular) {
            is PopularCurrencyState.Loading -> {
                item {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
            is PopularCurrencyState.Error -> {
                item {
                    Box(Modifier.fillMaxSize()) {
                        Text(
                            text = state.errorMessage,
                            style = MaterialTheme.typography.h4,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            is PopularCurrencyState.Success -> {
                items(state.popularCurrencies) { item ->
                    CurrencyItemScreen(item = item, onFavoriteClick = { favoriteItem ->
                        if (!favoriteItem.isFavorite)
                            popularViewModel.addToFavorite(item)
                        else
                            popularViewModel.removeFromFavorites(item)
                    }, textStyle = MaterialTheme.typography.h6)
                }
            }
        }
    }
}