package dev.victorhernandez.tvshows.data.di

import com.squareup.moshi.Moshi
import dev.victorhernandez.tvshows.data.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteModule {

    fun provideRetrofit(
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()
}