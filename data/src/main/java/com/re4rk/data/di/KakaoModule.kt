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
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class KakaoBrainUrlQualifier

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

    @KakaoBrainRetrofitQualifier
    @Provides
    fun provideKakaoRetrofit(@KakaoBrainUrlQualifier baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
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
