package dev.victorhernandez.tvshows.data.model.mapper

import dev.victorhernandez.tvshows.data.model.TvShowListItemApiModel
import dev.victorhernandez.tvshows.data.model.TvShowListItemDataModel
import dev.victorhernandez.tvshows.data.model.TvShowPageApiModel
import dev.victorhernandez.tvshows.data.model.TvShowPageDataModel

fun TvShowListItemApiModel.toData(): TvShowListItemDataModel =
    TvShowListItemDataModel(
        id,
        name,
        originalName,
        originalLanguage,
        originCountry,
        overview,
        genreIds,
        firstAirDate,
        posterPath,
        backdropPath,
        popularity,
        voteAverage,
        voteCount
    )

fun TvShowPageApiModel.toData(): TvShowPageDataModel =
    TvShowPageDataModel(
        page,
        results.map { it.toData() },
        totalResults,
        totalPages
    )