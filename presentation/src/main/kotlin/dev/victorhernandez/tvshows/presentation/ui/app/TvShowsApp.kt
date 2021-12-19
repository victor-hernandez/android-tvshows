package dev.victorhernandez.tvshows.presentation.ui.app

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.common.TvShowsTopBar

@Composable
fun TvShowsApp() {

    val navController = rememberNavController()

    TVShowsTheme {
        Scaffold(
            topBar = { TvShowsTopBar() }
        ) {
            TvShowsNavHost(navController)
        }
    }
}