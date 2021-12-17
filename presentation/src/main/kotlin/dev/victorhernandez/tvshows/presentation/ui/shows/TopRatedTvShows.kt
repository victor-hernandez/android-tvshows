package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
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
    Scaffold(
        topBar = { TvShowsTopBar() }
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
}

@ExperimentalFoundationApi
@Composable
fun TopRatedTvShowsPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        TopRatedTvShows(
            shows = TvShows
        ) { }
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