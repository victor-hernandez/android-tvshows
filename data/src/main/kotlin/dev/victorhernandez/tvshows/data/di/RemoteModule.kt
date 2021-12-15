package dev.victorhernandez.tvshows.data.di

import com.squareup.moshi.Moshi
import dev.victorhernandez.tvshows.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteModule {

    private const val API_KEY_QUERY_PARAM = "api_key"

    fun provideRetrofit(
        okHttpClient: OkHttpClient = provideOkHttpClient(),
        moshi: Moshi = provideMoshi()
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    fun provideOkHttpClient(
        apiKeyInterceptor: Interceptor = provideApiKeyInterceptor()
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().let { request ->
                request.newBuilder()
                    .url(
                        request.url().newBuilder()
                            .addQueryParameter(API_KEY_QUERY_PARAM, BuildConfig.API_KEY)
                            .build()
                    )
                    .build()
            }
        )
    }

    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

}