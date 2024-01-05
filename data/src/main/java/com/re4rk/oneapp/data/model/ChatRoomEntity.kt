package com.re4rk.oneapp.data.model

import com.re4rk.oneapp.domain.model.ChatRoom
import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomEntity(
    val title: String,
    val profile: String,
    val messageCount: Int,
    val lastMessage: String,
    val lastMessageTime: String,
    val status: String,
    val id: Int,
)

fun ChatRoomEntity.toDomain(): ChatRoom {
    return ChatRoom(
        id = this.id,
        lastMessage = this.lastMessage,
        lastMessageTime = this.lastMessageTime,
        messageCount = this.messageCount,
        profile = this.profile,
        status = this.status,
        title = this.title,
    )
}
