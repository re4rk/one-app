package com.re4rk.oneapp.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.re4rk.oneapp.data.repository.ChatRetrofitRepository
import com.re4rk.oneapp.data.repository.PracticeInMemoryRepository
import com.re4rk.oneapp.data.service.ChatService
import com.re4rk.oneapp.domain.repository.ChatRepository
import com.re4rk.oneapp.domain.repository.PracticeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideBaseUrl() = "http://10.0.2.2:8080/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideChatService(retrofit: Retrofit): ChatService {
        return retrofit.create(ChatService::class.java)
    }

    @Singleton
    @Provides
    fun provideChatRepository(chatService: ChatService): ChatRepository {
        return ChatRetrofitRepository(chatService)
    }

    @Singleton
    @Provides
    fun providePracticeRepository(): PracticeRepository {
        return PracticeInMemoryRepository()
    }
}
