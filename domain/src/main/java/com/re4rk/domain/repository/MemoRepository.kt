package com.re4rk.domain.repository

import com.re4rk.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    fun getMemos(): Flow<List<Memo>>
}