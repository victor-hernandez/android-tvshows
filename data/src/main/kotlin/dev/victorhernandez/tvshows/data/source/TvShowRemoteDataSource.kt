package dev.victorhernandez.tvshows.data.source

import dev.victorhernandez.tvshows.data.model.TvShowPageDataModel

interface TvShowRemoteDataSource {

    suspend fun getTopRatedShows(page: Int = 1): TvShowPageDataModel

    suspend fun getSimilarShows(showId: Int, page: Int = 1): TvShowPageDataModel

}