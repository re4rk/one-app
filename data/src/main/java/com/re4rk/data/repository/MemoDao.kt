package com.re4rk.data.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Query("SELECT * FROM memos")
    fun getMemoEntities(): Flow<List<MemoEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMemoEntity(memoEntity: MemoEntity)
}
