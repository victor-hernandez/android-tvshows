package dev.victorhernandez.tvshows.presentation.ui.shows

import dev.victorhernandez.tvshows.presentation.model.TvShowListItemUiModel

data class TopRatedTvShowsUiState(
    val shows: List<TvShowListItemUiModel> = emptyList(),
    val loading: Boolean = false
)