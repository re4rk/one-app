package com.re4rk.presentation.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.diPractice.DiPracticeActivity
import com.re4rk.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import com.re4rk.presentation.ui.memo.MemoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contents = (0..10).flatMap {
            listOf(
                Content(
                    intent = ChatRoomListActivity.getIntent(this),
                    description = "Chat Room List",
                ),
                Content(
                    intent = LifecycleTackerActivity.getIntent(this),
                    description = "Lifecycle Tacker Room List",
                ),
                Content(
                    intent = DiPracticeActivity.getIntent(this),
                    description = "DI Practice",
                ),
                Content(
                    intent = MemoActivity.getIntent(this),
                    description = "Memo",
                ),
            )
        }

        setContent {
            HomeRoute(contents)
        }
    }
}
