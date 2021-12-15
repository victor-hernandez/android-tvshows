package dev.victorhernandez.tvshows.domain.model

data class TvShowPageDomainModel(
    val page: Int,
    val results: List<TvShowListItemDomainModel>,
    val totalResults: Int,
    val totalPages: Int
)