package com.re4rk.oneApp.ui

import com.re4rk.domain.model.ChatRoom

sealed interface HomeState {
    data class Success(val chatRooms: List<ChatRoom>) : HomeState
    data class Error(val error: Throwable?) : HomeState
    object Loading : HomeState
}
