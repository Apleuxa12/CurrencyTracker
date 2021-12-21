package com.ddmukhin.currencytracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ddmukhin.currencytracker.navigation.spec.PopularScreenSpec
import com.ddmukhin.currencytracker.viewmodel.FavoritesViewModel
import com.ddmukhin.currencytracker.viewmodel.GlobalViewModel
import com.ddmukhin.currencytracker.viewmodel.PopularViewModel
import com.ddmukhin.currencytracker.viewmodel.SortViewModel
import com.ddmukhin.currencytracker.viewmodel.state.FavoritesCurrencyState
import timber.log.Timber

@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    globalViewModel: GlobalViewModel = hiltViewModel(),
    sortViewModel: SortViewModel = hiltViewModel()
) {
    val favorites by favoritesViewModel.state.collectAsState()
    val sortState by sortViewModel.state.collectAsState()

    sortState.list.forEach {
        favoritesViewModel.applySort(it)
    }

    favoritesViewModel.loadFavorites()

    LazyColumn{
        item{
            GlobalCurrencyScreen(navController, globalViewModel)
        }

        when(val state = favorites){
            is FavoritesCurrencyState.Loading -> {
                item{
                    Box(Modifier.fillMaxSize()){
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
            is FavoritesCurrencyState.Success -> {
                items(state.list){ item ->
                    CurrencyItemScreen(item = item, onFavoriteClick = {
                        if(it.isFavorite) favoritesViewModel.removeFromFavorites(it)
                    })
                }
            }
        }
    }
}