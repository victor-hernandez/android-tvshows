package dev.victorhernandez.tvshows.domain.model

data class TvShowListItemDomainModel(
    val id: Int,
    val name: String,
    val originalName: String,
    val originalLanguage: String,
    val originCountry: List<String>,
    val overview: String,
    val genreIds: List<Int>,
    val firstAirDate: String,
    val posterPath: String?,
    val backdropPath: String?,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)