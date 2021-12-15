package dev.victorhernandez.tvshows.domain.repository

import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel

interface TvShowRepository {

    suspend fun getTopRatedShows(page: Int = 1): TvShowPageDomainModel

}