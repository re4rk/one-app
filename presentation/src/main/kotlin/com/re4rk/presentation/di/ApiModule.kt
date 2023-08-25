package com.re4rk.presentation.di

import com.re4rk.domain.repository.ChatRepository
import com.re4rk.presentation.repository.ChatMockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideChatRepository(): ChatRepository {
        return ChatMockRepository()
    }
}
