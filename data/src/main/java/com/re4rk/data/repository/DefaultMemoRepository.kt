package com.re4rk.data.repository

import com.re4rk.domain.model.Memo
import com.re4rk.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultMemoRepository @Inject constructor() : MemoRepository {
    private var count = 10

    private val memos = MutableList(1) {
        Memo(
            id = 0,
            title = "title",
            content = "contents",
        )
    }

    override fun getMemos(): Flow<List<Memo>> = flow { emit(memos) }

    override suspend fun insertMemo(memo: Memo) {
        memos.add(memo)
        count++
    }
}
