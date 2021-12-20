package dev.victorhernandez.tvshows.data.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.victorhernandez.tvshows.data.remote.TvShowApiService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Reusable
    fun provideTvShowApiService(retrofit: Retrofit): TvShowApiService =
        retrofit.create(TvShowApiService::class.java)

}