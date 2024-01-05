package com.re4rk.oneapp.domain.repository

import com.re4rk.oneapp.domain.model.ChatRoom
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun getChatRooms(): Flow<List<ChatRoom>>
}
