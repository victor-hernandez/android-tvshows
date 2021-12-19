package dev.victorhernandez.tvshows.presentation.ui.app

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.common.TvShowsTopBar

@Composable
fun TvShowsApp() {

    val navController = rememberNavController()
    var canNavigateUp by remember { mutableStateOf(navController.previousBackStackEntry != null) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        canNavigateUp = controller.previousBackStackEntry != null
    }

    TVShowsTheme {
        Scaffold(
            topBar = { TvShowsTopBar(canNavigateUp) { navController.navigateUp() } }
        ) {
            TvShowsNavHost(navController)
        }
    }
}