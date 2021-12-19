package dev.victorhernandez.tvshows.presentation.ui.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ListEndReachedHandler(
    listState: LazyListState,
    threshold: Int = 2,
    onListEndReached: () -> Unit
) {

    val shouldLoadMore = remember {
        derivedStateOf {
            listState.layoutInfo.let { layoutInfo ->
                (layoutInfo.visibleItemsInfo
                    .lastOrNull()?.index ?: 0) + 1 > (layoutInfo.totalItemsCount - threshold)
            }
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect { onListEndReached() }
    }
}