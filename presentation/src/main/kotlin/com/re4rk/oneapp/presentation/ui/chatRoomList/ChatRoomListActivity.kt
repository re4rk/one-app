package com.re4rk.oneapp.presentation.ui.chatRoomList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatRoomListActivity : ComponentActivity() {
    private val viewModel: ChatRoomListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeRoot(viewModel)
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, ChatRoomListActivity::class.java)
        }
    }
}
