package dev.victorhernandez.tvshows.presentation.mapper

import dev.victorhernandez.tvshows.domain.model.TvShowListItemDomainModel
import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.presentation.model.TvShowListItemUiModel

fun TvShowListItemDomainModel.toUi(): TvShowListItemUiModel =
    TvShowListItemUiModel(
        id,
        name,
        backdropPath ?: posterPath,
        voteAverage
    )

fun TvShowPageDomainModel.toUi(): List<TvShowListItemUiModel> =
    results.map { it.toUi() }