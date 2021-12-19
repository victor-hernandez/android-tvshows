package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel
import dev.victorhernandez.tvshows.presentation.model.TvShowListItemUiModel
import dev.victorhernandez.tvshows.presentation.theme.SpacingMedium
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.common.TvShowsTopBar
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows

internal const val TestTagTopRatedTvShowsLazyVerticalGrid = "TestTagTopRatedTvShowsLazyVerticalGrid"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopRatedTvShows(
    shows: List<TvShowListItemUiModel>,
    onClick: (TvShowListItemUiModel) -> Unit
) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(SpacingMedium),
        modifier = Modifier.testTag(TestTagTopRatedTvShowsLazyVerticalGrid)
    ) {
        items(shows) { show ->
            key(show.id) {
                TvShowListItem(show, onClick)
            }
        }
    }
}

@Composable
fun TopRatedTvShowsScreen(
    viewModel: TopRatedTvShowsViewModel = hiltViewModel(),
    onNavigateToShowDetails: (TvShowDetailUiModel) -> Unit
) {
    val state = viewModel.uiState.collectAsState(TopRatedTvShowsUiState())
    LaunchedEffect(viewModel) {
        viewModel.loadTopRatedTvShows()
    }
    TopRatedTvShows(state.value.shows) {
        onNavigateToShowDetails.invoke(
            TvShowDetailUiModel(
                it.id,
                it.name,
                it.image,
                it.voteAverage,
                "Dummy overview"
            )
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun TopRatedTvShowsPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        Scaffold(
            topBar = { TvShowsTopBar() }
        ) {
            TopRatedTvShows(
                shows = TvShows
            ) { }
        }
    }
}

@ExperimentalFoundationApi
@Preview(name = "Top rated TV show preview light")
@Composable
private fun TopRatedTvShowsPreviewLight() {
    TopRatedTvShowsPreview(false)
}

@ExperimentalFoundationApi
@Preview(name = "Top rated TV show preview dark")
@Composable
private fun TopRatedTvShowsPreviewDark() {
    TopRatedTvShowsPreview(true)
}