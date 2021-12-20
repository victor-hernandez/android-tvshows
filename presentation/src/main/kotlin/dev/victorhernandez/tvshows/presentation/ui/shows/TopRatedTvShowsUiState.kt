package dev.victorhernandez.tvshows.presentation.ui.shows

import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel

data class TopRatedTvShowsUiState(
    val shows: List<TvShowUiModel> = emptyList(),
    val loading: Boolean = false
)