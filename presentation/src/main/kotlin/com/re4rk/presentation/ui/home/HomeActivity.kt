package com.re4rk.presentation.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contents = listOf(
            Content(
                intent = ChatRoomListActivity.getIntent(this),
                description = "Chat Room List",
            ),
        )
        setContent {
            HomeRoot(contents)
        }
    }
}
