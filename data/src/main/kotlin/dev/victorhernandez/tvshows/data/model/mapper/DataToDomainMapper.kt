package dev.victorhernandez.tvshows.data.model.mapper

import dev.victorhernandez.tvshows.data.model.TvShowListItemDataModel
import dev.victorhernandez.tvshows.data.model.TvShowPageDataModel
import dev.victorhernandez.tvshows.domain.model.TvShowListItemDomainModel
import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel

fun TvShowListItemDataModel.toDomain(): TvShowListItemDomainModel =
    TvShowListItemDomainModel(
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

fun TvShowPageDataModel.toDomain(): TvShowPageDomainModel =
    TvShowPageDomainModel(
        page,
        results.map { it.toDomain() },
        totalResults,
        totalPages
    )