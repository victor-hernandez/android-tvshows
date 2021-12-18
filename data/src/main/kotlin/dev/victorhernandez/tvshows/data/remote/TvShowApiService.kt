package dev.victorhernandez.tvshows.data.remote

import dev.victorhernandez.tvshows.data.model.TvShowPageApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowApiService {

    @GET("tv/top_rated")
    suspend fun getTopRatedShows(
        @Query("page") page: Int = 1
    ): TvShowPageApiModel

    @GET("tv/{show_id}/similar")
    suspend fun getSimilarShows(
        @Path("show_id") showId: Int,
        @Query("page") page: Int = 1
    ): TvShowPageApiModel

}