package ru.clonsaldafon.alphaecosystem.presentation.navigation

import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.clonsaldafon.alphaecosystem.presentation.view.CardDataScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.CardData.route,
        enterTransition = { fadeIn(tween(400)) },
        exitTransition = { ExitTransition.KeepUntilTransitionsFinished }
    ) {
        composable(route = Routes.CardData.route) {
            CardDataScreen(
                modifier = modifier,
                navController = navController
            )
        }
    }
}