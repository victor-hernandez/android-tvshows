package dev.victorhernandez.tvshows.presentation.ui.show

import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel

data class TvShowDetailsUiState(
    val show: TvShowUiModel? = null,
    val shows: List<TvShowUiModel> = emptyList(),
    val loading: Boolean = false
)