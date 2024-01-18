package com.re4rk.oneapp.core.network.coinone.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.re4rk.oneapp.core.network.coinone.CoinoneOrderBookRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoinoneBaseUrlQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoinoneRetrofitConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoinoneRetrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @CoinoneBaseUrlQualifier
    fun provideBaseURL(): String = "https://api.coinone.co.kr"

    @Provides
    @Singleton
    @CoinoneRetrofitConverterFactory
    fun provideRetrofitConverterFactory(): retrofit2.Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @CoinoneRetrofit
    fun provideNormalRetrofit(
        @CoinoneBaseUrlQualifier baseUrl: String,
        @CoinoneRetrofitConverterFactory converterFactory: retrofit2.Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(
            OkHttpClient().newBuilder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
                ).build(),
        )
        .build()

    @Provides
    fun provideCoinoneOrderBookRetrofitService(
        @CoinoneRetrofit retrofit: Retrofit,
    ): CoinoneOrderBookRetrofitService =
        retrofit.create(CoinoneOrderBookRetrofitService::class.java)
}
