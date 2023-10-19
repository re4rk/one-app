package com.re4rk.data.repository

import com.re4rk.domain.model.Memo
import com.re4rk.domain.repository.MemoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DefaultMemoRepository : MemoRepository {
    // TODO("페이크에서 실제 데이터로 변경 작업 필요")
    override fun getMemos(): Flow<List<Memo>> = flow {
        emit(
            List(10) {
                Memo(
                    id = 0,
                    title = "title",
                    content = "content",
                )
            },
        )
    }
}
