package dev.victorhernandez.tvshows.presentation.model

data class TvShowListItemUiModel(
    val id: Int,
    val name: String,
    val image: String?,
    val voteAverage: Double,
)