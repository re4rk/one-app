package com.re4rk.oneapp.di

import com.re4rk.oneapp.data.database.ArkDatabase
import com.re4rk.oneapp.data.repository.MemoDao
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
