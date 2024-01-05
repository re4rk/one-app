package com.re4rk.presentation.ui.chatRoomList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.domain.usecase.GetChatRoomsUseCase
import com.re4rk.presentation.util.Result
import com.re4rk.presentation.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatRoomListViewModel @Inject constructor(
    getChatRoomsUseCase: GetChatRoomsUseCase,
) : ViewModel() {
    val chatRoomListState: StateFlow<ChatRoomListState> = chatRoomListState(
        getChatRoomsUseCase = getChatRoomsUseCase,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ChatRoomListState.Loading,
    )
}

private fun chatRoomListState(
    getChatRoomsUseCase: GetChatRoomsUseCase,
): Flow<ChatRoomListState> {
    return getChatRoomsUseCase().asResult().map { result ->
        when (result) {
            is Result.Error -> ChatRoomListState.Error(result.exception)
            is Result.Loading -> ChatRoomListState.Loading
            is Result.Success -> ChatRoomListState.Success(result.data)
        }
    }
}
