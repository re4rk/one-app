package com.re4rk.domain.model

data class ChatRoom(
    val id: Int,
    val lastMessage: String,
    val lastMessageTime: String,
    val messageCount: Int,
    val profile: String,
    val status: String,
    val title: String,
)
