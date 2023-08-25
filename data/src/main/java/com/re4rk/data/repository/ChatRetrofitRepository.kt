package com.re4rk.data.repository

import com.re4rk.data.model.toDomain
import com.re4rk.data.service.ChatService
import com.re4rk.domain.model.ChatRoom
import com.re4rk.domain.repository.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChatRetrofitRepository @Inject constructor(
    private val chatService: ChatService,
) : ChatRepository {
    override fun getChatRooms(): Flow<List<ChatRoom>> {
        println("getChatRooms")
        return flow {
            emit(chatService.getChatRooms().body()?.map { it.toDomain() } ?: emptyList<ChatRoom>())
        }.flowOn(Dispatchers.IO)
    }
}
