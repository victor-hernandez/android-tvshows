package dev.victorhernandez.tvshows.presentation.mapper

import dev.victorhernandez.tvshows.domain.model.TvShowListItemDomainModel
import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.presentation.model.TvShowUiModel

fun TvShowListItemDomainModel.toUi(): TvShowUiModel =
    TvShowUiModel(
        id = id,
        name = name,
        image = "https://image.tmdb.org/t/p/w500/$posterPath",
        imageBig = "https://image.tmdb.org/t/p/original/$posterPath",
        voteAverage = voteAverage,
        overview = overview
    )

fun TvShowPageDomainModel.toUi(): List<TvShowUiModel> =
    results.map { it.toUi() }