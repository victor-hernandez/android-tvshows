package dev.victorhernandez.tvshows.presentation.model

data class TvShowListUiState(
    val shows: List<TvShowListItemUiModel> = emptyList(),
    val loading: Boolean = false
)