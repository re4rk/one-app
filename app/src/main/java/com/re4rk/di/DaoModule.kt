package com.re4rk.di

import com.re4rk.data.database.ArkDatabase
import com.re4rk.data.repository.MemoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideMemoDao(arkDatabase: ArkDatabase): MemoDao = arkDatabase.memoDao()
}
