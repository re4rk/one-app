package com.re4rk.di

import android.content.Context
import androidx.room.Room
import com.re4rk.data.database.ArkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesNiaDatabase(
        @ApplicationContext context: Context,
    ): ArkDatabase = Room.databaseBuilder(
        context,
        ArkDatabase::class.java,
        "ark-database",
    ).build()
}
