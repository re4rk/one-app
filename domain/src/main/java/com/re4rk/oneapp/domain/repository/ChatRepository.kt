package com.re4rk.domain.repository

import com.re4rk.domain.model.ChatRoom
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChatRooms(): Flow<List<ChatRoom>>
}
