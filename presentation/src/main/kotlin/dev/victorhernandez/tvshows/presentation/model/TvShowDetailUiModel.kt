package dev.victorhernandez.tvshows.presentation.model

data class TvShowDetailUiModel(
    val id: Int,
    val name: String,
    val image: String?,
    val voteAverage: Double,
    val overview: String
)