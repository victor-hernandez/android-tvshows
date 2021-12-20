package dev.victorhernandez.tvshows.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.victorhernandez.tvshows.data.source.TvShowRemoteDataSource
import dev.victorhernandez.tvshows.data.source.TvShowRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindTvShowRemoteDataSource(dataSource: TvShowRemoteDataSourceImpl): TvShowRemoteDataSource

}