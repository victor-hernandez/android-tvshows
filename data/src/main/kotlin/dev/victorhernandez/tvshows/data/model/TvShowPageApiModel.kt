package dev.victorhernandez.tvshows.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvShowPageApiModel(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<TvShowListItemApiModel>,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)