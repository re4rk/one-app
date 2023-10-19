package com.re4rk.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.re4rk.data.repository.DefaultKoGptRepository
import com.re4rk.data.service.KoGptService
import com.re4rk.domain.repository.KoGptRepository
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
private annotation class KakaoBrainUrlQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class KakaoBrainOkHttpClientQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class KakaoBrainRetrofitQualifier

@InstallIn(SingletonComponent::class)
@Module
object KakaoModule {
    @KakaoBrainUrlQualifier
    @Singleton
    @Provides
    fun provideKoGptUrl() = "https://api.kakaobrain.com"

    @KakaoBrainOkHttpClientQualifier
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY },
        )
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.HEADERS },
        )
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC },
        )
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE },
        )
        .build()

    @KakaoBrainRetrofitQualifier
    @Provides
    fun provideKakaoRetrofit(
        @KakaoBrainUrlQualifier baseUrl: String,
        @KakaoBrainOkHttpClientQualifier okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideKoGptService(@KakaoBrainRetrofitQualifier retrofit: Retrofit): KoGptService {
        return retrofit.create(KoGptService::class.java)
    }

    @Singleton
    @Provides
    fun provideKoGptRepository(koGptService: KoGptService): KoGptRepository =
        DefaultKoGptRepository(koGptService)
}
