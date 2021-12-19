package dev.victorhernandez.tvshows.presentation.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel
import dev.victorhernandez.tvshows.presentation.ui.show.TvShowDetailsScreen
import dev.victorhernandez.tvshows.presentation.ui.shows.TopRatedTvShowsScreen

@Composable
fun TvShowsNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "top_rated_shows"
    ) {
        composable(
            route = "top_rated_shows"
        ) {
            TopRatedTvShowsScreen(
                onNavigateToShowDetails = { show -> navigateToTvShowDetails(navController, show) }
            )
        }

        composable(
            route = "tv_show_details?" +
                    "id={id}&" +
                    "name={name}&" +
                    "image={image}&" +
                    "voteAverage={voteAverage}&" +
                    "overview={overview}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("image") { type = NavType.StringType },
                navArgument("voteAverage") { type = NavType.StringType },
                navArgument("overview") { type = NavType.StringType }
            )
        ) { entry ->
            TvShowDetailsScreen(
                entry.arguments!!.let { args ->
                    TvShowDetailUiModel(
                        args.getInt("id"),
                        args.getString("name")!!,
                        args.getString("image"),
                        args.getString("voteAverage")?.toDouble() ?: .0,
                        args.getString("overview")!!
                    )
                },
                { navController.navigateUp() }
            )
        }
    }
}

private fun navigateToTvShowDetails(
    navController: NavHostController,
    show: TvShowDetailUiModel
) {
    navController.navigate(
        "tv_show_details?" +
                "id=${show.id}&" +
                "name=${show.name}&" +
                "image=${show.image ?: ""}&" +
                "voteAverage=${show.voteAverage}&" +
                "overview=${show.overview}"
    )
}