package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel
import dev.victorhernandez.tvshows.presentation.ui.common.ListEndReachedHandler

internal const val TestTagRelatedTvShowsLazyRow = "TestTagRelatedTvShowsLazyRow"

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun RelatedTvShows(
    shows: List<TvShowDetailUiModel>,
    onListEndReached: () -> Unit,
) {

    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        flingBehavior = rememberSnapperFlingBehavior(listState),
        modifier = Modifier
            .testTag(TestTagRelatedTvShowsLazyRow)
    ) {
        items(shows) { show ->
            Box(modifier = Modifier.fillParentMaxWidth()) {
                TvShowDetails(show)
            }
        }
    }

    ListEndReachedHandler(
        listState = listState,
        threshold = 1,
        onListEndReached = onListEndReached
    )
}