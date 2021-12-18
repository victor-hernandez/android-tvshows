package dev.victorhernandez.tvshows.presentation.mapper

import dev.victorhernandez.tvshows.domain.model.TvShowListItemDomainModel
import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.presentation.model.TvShowDetailUiModel
import dev.victorhernandez.tvshows.presentation.model.TvShowListItemUiModel

fun TvShowListItemDomainModel.toListUiModel(): TvShowListItemUiModel =
    TvShowListItemUiModel(
        id = id,
        name = name,
        image = "https://image.tmdb.org/t/p/w500/$posterPath",
        voteAverage = voteAverage
    )

fun TvShowPageDomainModel.toListUiModel(): List<TvShowListItemUiModel> =
    results.map { it.toListUiModel() }

fun TvShowListItemDomainModel.toDetailUiModel(): TvShowDetailUiModel =
    TvShowDetailUiModel(
        id = id,
        name = name,
        image = "https://image.tmdb.org/t/p/original/${backdropPath ?: posterPath}",
        voteAverage = voteAverage,
        overview = overview
    )

fun TvShowPageDomainModel.toDetailUiModel(): List<TvShowDetailUiModel> =
    results.map { it.toDetailUiModel() }