package com.re4rk.oneapp.core.networkcoinone.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlQualifier

object ApiModule {
    @Provides
    fun provideBaseURL(): String = "https://api.coinone.co.kr"

    @Provides
    @Singleton
    fun provideRetrofitConverterFactory(): retrofit2.Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun providesNormalRetrofit(
        @BaseUrlQualifier baseUrl: String,
        converterFactory: retrofit2.Converter.Factory,
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
}
