package com.plcoding.cryptocurrencyappyt.presentation

sealed class Screen(val route: String) {
    object CoinListScreen: Screen("coin-list-screen")
    object CoinDetailsScreen: Screen("coin-details-screen")
}
