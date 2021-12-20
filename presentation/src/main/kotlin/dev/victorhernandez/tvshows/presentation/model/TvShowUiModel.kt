package dev.victorhernandez.tvshows.presentation.model

data class TvShowUiModel(
    val id: Int,
    val name: String,
    val image: String?,
    val imageBig: String?,
    val voteAverage: Double,
    val overview: String
)