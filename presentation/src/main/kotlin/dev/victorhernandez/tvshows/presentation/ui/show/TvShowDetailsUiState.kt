package dev.victorhernandez.tvshows.presentation.ui.show

import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel

data class TvShowDetailsUiState(
    val show: TvShowDetailUiModel,
    val shows: List<TvShowDetailUiModel> = emptyList(),
    val loading: Boolean = false
)