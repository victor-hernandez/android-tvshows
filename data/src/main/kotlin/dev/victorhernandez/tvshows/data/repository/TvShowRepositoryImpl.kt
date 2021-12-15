package dev.victorhernandez.tvshows.data.repository

import dev.victorhernandez.tvshows.data.model.mapper.toDomain
import dev.victorhernandez.tvshows.data.source.TvShowRemoteDataSource
import dev.victorhernandez.tvshows.domain.model.TvShowPageDomainModel
import dev.victorhernandez.tvshows.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val remoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {

    override suspend fun getTopRatedShows(page: Int): TvShowPageDomainModel =
        remoteDataSource.getTopRatedShows(page).toDomain()

}