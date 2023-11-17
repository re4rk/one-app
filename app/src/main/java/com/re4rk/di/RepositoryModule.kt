package com.re4rk.di

import com.re4rk.data.repository.DefaultMemoRepository
import com.re4rk.domain.repository.MemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMemoRepository(): MemoRepository = DefaultMemoRepository()
}
