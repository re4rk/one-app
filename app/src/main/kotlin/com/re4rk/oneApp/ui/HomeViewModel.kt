package com.re4rk.oneApp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.domain.repository.ChatRepository
import com.re4rk.oneApp.util.Result
import com.re4rk.oneApp.util.asResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val chatRepository: ChatRepository,
) : ViewModel() {
    val homeState: StateFlow<HomeState> = homeState(
        chatRepository = chatRepository,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeState.Loading,
    )
}

private fun homeState(
    chatRepository: ChatRepository,
): Flow<HomeState> {
    return chatRepository.getChatRooms().asResult().map { result ->
        when (result) {
            is Result.Error -> HomeState.Error(result.exception)
            is Result.Loading -> HomeState.Loading
            is Result.Success -> HomeState.Success(result.data)
        }
    }
}
