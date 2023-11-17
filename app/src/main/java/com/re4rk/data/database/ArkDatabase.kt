package com.re4rk.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.re4rk.data.repository.MemoDao
import com.re4rk.data.repository.MemoEntity

@Database(
    entities = [
        MemoEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class ArkDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
