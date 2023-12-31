package com.re4rk.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.re4rk.app.ui.theme.OneAppTheme
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.diPractice.DiPracticeActivity
import com.re4rk.presentation.ui.home.Content
import com.re4rk.presentation.ui.home.navigation.homeNavigationRoute
import com.re4rk.presentation.ui.home.navigation.homeScreen
import com.re4rk.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import com.re4rk.presentation.ui.memo.MemoActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            OneAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = homeNavigationRoute,
                ) {
                    homeScreen(contents = getFakeHomeScreenContents(this@MainActivity))
                }
            }
        }
    }

    companion object {
        fun getFakeHomeScreenContents(activity: ComponentActivity) = (0..10).flatMap {
            listOf(
                Content(
                    intent = ChatRoomListActivity.getIntent(activity),
                    description = "Chat Room List",
                ),
                Content(
                    intent = LifecycleTackerActivity.getIntent(activity),
                    description = "Lifecycle Tacker Room List",
                ),
                Content(
                    intent = DiPracticeActivity.getIntent(activity),
                    description = "DI Practice",
                ),
                Content(
                    intent = MemoActivity.getIntent(activity),
                    description = "Memo",
                ),
            )
        }
    }
}
