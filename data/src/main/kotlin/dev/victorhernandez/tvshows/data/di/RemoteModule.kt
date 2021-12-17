package dev.victorhernandez.tvshows.data.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.victorhernandez.tvshows.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val API_KEY_QUERY_PARAM = "api_key"
    private const val API_KEY_INTERCEPTOR = "API_KEY_INTERCEPTOR"
    private const val LOGGING_INTERCEPTOR = "LOGGING_INTERCEPTOR"
    private const val LOGGING_LEVEL = "LOGGING_LEVEL"

    @Provides
    @Reusable
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Reusable
    fun provideOkHttpClient(
        @Named(API_KEY_INTERCEPTOR) apiKeyInterceptor: Interceptor,
        @Named(LOGGING_INTERCEPTOR) loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Reusable
    @Named(API_KEY_INTERCEPTOR)
    fun provideApiKeyInterceptor(): Interceptor = Interceptor { chain ->
        val r = chain.request().let { request ->
            request.newBuilder()
                .url(
                    request.url().newBuilder()
                        .addQueryParameter(API_KEY_QUERY_PARAM, BuildConfig.API_KEY)
                        .build()
                )
                .build()
        }
        chain.proceed(r)
    }

    @Provides
    @Reusable
    @Named(LOGGING_INTERCEPTOR)
    fun provideHttpLoggingInterceptor(
        @Named(LOGGING_LEVEL)
        loggingLevel: HttpLoggingInterceptor.Level
    ): Interceptor =
        HttpLoggingInterceptor().apply { level = loggingLevel }

    @Provides
    @Named(LOGGING_LEVEL)
    fun provideHttpLoggingLevel(): HttpLoggingInterceptor.Level =
        if (BuildConfig.LOGGING_ENABLED) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Reusable
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .build()

}