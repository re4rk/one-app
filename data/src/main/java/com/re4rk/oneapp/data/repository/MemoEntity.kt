package com.re4rk.data.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.re4rk.domain.model.Memo

@Entity(tableName = "memos")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
) {
    fun toDomain() = Memo(
        id = id.toInt(),
        title = title,
        content = content,
    )

    companion object {
        fun from(memo: Memo) = MemoEntity(
            title = memo.title,
            content = memo.content,
        )
    }
}
