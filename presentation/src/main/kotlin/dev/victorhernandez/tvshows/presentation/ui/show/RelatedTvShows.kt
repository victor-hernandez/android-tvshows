package dev.victorhernandez.tvshows.presentation.ui.show

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel
import dev.victorhernandez.tvshows.presentation.ui.common.ListEndReachedHandler
import kotlinx.coroutines.launch

internal const val TestTagRelatedTvShowsLazyRow = "TestTagRelatedTvShowsLazyRow"

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun RelatedTvShows(
    shows: List<TvShowUiModel>,
    onListEndReached: () -> Unit,
    onBackPressed: () -> Unit
) {

    val scope = rememberCoroutineScope()
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

    BackHandler {
        if (listState.firstVisibleItemIndex > 0) {
            scope.launch {
                listState.animateScrollToItem(0)
            }
        }
        else {
            onBackPressed()
        }
    }
}