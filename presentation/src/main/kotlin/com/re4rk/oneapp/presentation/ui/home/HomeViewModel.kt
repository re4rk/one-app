package com.re4rk.oneapp.presentation.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.re4rk.oneapp.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.oneapp.presentation.ui.diPractice.DiPracticeActivity
import com.re4rk.oneapp.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    fun getHomeScreenContents(context: Context) = listOf(
        Content(
            intent = ChatRoomListActivity.getIntent(context),
            description = "Chat Room List",
        ),
        Content(
            intent = LifecycleTackerActivity.getIntent(context),
            description = "Lifecycle Tacker Room List",
        ),
        Content(
            intent = DiPracticeActivity.getIntent(context),
            description = "DI Practice",
        ),
    )
}
