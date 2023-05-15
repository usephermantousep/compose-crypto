package com.plcoding.cryptocurrencyappyt.presentation

sealed class Screen (val route:String){
    object CoinListScreen: Screen("coin_list_screen")
    object CoinDetailcreen: Screen("coin_detail_screen")
}
