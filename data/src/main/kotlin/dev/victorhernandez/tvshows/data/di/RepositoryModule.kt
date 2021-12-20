package dev.victorhernandez.tvshows.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.victorhernandez.tvshows.data.repository.TvShowRepositoryImpl
import dev.victorhernandez.tvshows.domain.repository.TvShowRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTvShowRepository(repository: TvShowRepositoryImpl): TvShowRepository

}