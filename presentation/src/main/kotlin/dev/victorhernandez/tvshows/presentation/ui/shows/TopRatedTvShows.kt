package dev.victorhernandez.tvshows.presentation.ui.shows

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel
import dev.victorhernandez.tvshows.presentation.theme.SpacingMedium
import dev.victorhernandez.tvshows.presentation.theme.TVShowsTheme
import dev.victorhernandez.tvshows.presentation.ui.common.ListEndReachedHandler
import dev.victorhernandez.tvshows.presentation.ui.common.TvShowsTopBar
import dev.victorhernandez.tvshows.presentation.ui.example.TvShows

internal const val TestTagTopRatedTvShowsLazyVerticalGrid = "TestTagTopRatedTvShowsLazyVerticalGrid"

@Composable
fun TopRatedTvShowsScreen(
    viewModel: TopRatedTvShowsViewModel = hiltViewModel(),
    onNavigateToShowDetails: (TvShowUiModel) -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    TopRatedTvShows(
        state.value.loading,
        state.value.shows,
        { viewModel.loadMoreShows() }
    ) {
        onNavigateToShowDetails(it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TopRatedTvShows(
    loading: Boolean,
    shows: List<TvShowUiModel>,
    onListEndReached: () -> Unit,
    onClick: (TvShowUiModel) -> Unit
) {
    val listState = rememberLazyListState()

    if (shows.isEmpty() && loading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )
        }
    }
    else {
        LazyVerticalGrid(
            state = listState,
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

        ListEndReachedHandler(
            listState = listState,
            onListEndReached = onListEndReached
        )
    }
}

@ExperimentalFoundationApi
@Composable
private fun TopRatedTvShowsPreview(darkTheme: Boolean) {
    TVShowsTheme(darkTheme) {
        Scaffold(
            topBar = { TvShowsTopBar(false) { } }
        ) {
            TopRatedTvShows(
                shows = TvShows,
                loading = false,
                onListEndReached = { }
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