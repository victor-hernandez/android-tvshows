package dev.victorhernandez.tvshows.data.model

data class TvShowPageDataModel(
    val page: Int,
    val results: List<TvShowListItemDataModel>,
    val totalResults: Int,
    val totalPages: Int
)