package ru.clonsaldafon.alphaecosystem.presentation.navigation

sealed class Routes(val route: String) {
    data object CardData : Routes("card_data")
    data object History : Routes("history")
}