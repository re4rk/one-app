package com.re4rk.oneApp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.re4rk.oneApp.repository.ChatMockRepository

class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel = HomeViewModel(
        chatRepository = ChatMockRepository(),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeRoot(viewModel)
        }
    }
}
