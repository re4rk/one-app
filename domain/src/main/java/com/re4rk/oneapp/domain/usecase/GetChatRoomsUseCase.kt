package com.re4rk.domain.usecase

import com.re4rk.domain.model.ChatRoom
import com.re4rk.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatRoomsUseCase @Inject constructor(
    private val repository: ChatRepository,
) {
    operator fun invoke(): Flow<List<ChatRoom>> = repository.getChatRooms()
}
