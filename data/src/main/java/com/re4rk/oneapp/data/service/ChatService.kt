package com.re4rk.data.service

import com.re4rk.data.model.ChatRoomEntity
import retrofit2.Response
import retrofit2.http.GET

interface ChatService {
    @GET("/api/chat-rooms")
    suspend fun getChatRooms(): Response<List<ChatRoomEntity>>
}
