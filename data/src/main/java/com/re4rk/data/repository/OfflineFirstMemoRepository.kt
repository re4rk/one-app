package com.re4rk.data.repository

import com.re4rk.domain.model.Memo
import com.re4rk.domain.repository.MemoRepository
import kotlinx.coroutines.flow.transform

class OfflineFirstMemoRepository(
    private val memoDao: MemoDao,
    private val defaultMemoRepository: DefaultMemoRepository,
) : MemoRepository {
    override fun getMemos() = memoDao.getMemoEntities()
        .transform { memoEntities ->
            emit(memoEntities.map { it.toDomain() })
        }

    override suspend fun insertMemo(memo: Memo) {
        defaultMemoRepository.getMemos().collect { memos ->
            println("memos: $memos")
            memos.forEach {
                memoDao.insertMemoEntity(MemoEntity.from(it))
            }
        }
    }
}
