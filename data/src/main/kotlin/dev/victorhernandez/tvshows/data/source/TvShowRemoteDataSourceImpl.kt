package dev.victorhernandez.tvshows.data.source

import dev.victorhernandez.tvshows.data.model.mapper.toData
import dev.victorhernandez.tvshows.data.remote.TvShowApiService
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(
    private val service: TvShowApiService
): TvShowRemoteDataSource {

    override suspend fun getTopRatedShows(page: Int) =
        service.getTopRatedShows(page).toData()

}