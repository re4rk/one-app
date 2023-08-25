package com.re4rk.presentation.ui.chatRoomList

import com.re4rk.domain.model.ChatRoom

sealed interface ChatRoomListState {
    data class Success(val chatRooms: List<ChatRoom>) : ChatRoomListState
    data class Error(val error: Throwable?) : ChatRoomListState
    object Loading : ChatRoomListState
}
