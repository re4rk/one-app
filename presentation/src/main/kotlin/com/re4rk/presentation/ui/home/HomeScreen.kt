package com.re4rk.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun HomeRoute(contents: List<Content>) {
    HomeScreen(contents)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(contents: List<Content>) = OneAppTheme(
    dynamicColor = false,
) {
    Scaffold(
        modifier = Modifier.fillMaxHeight(),
        content = {
            Column {
                contents.forEach { content -> CategoryItem(content) }
            }
        },
    )
}

@Composable
private fun CategoryItem(
    content: Content,
) {
    val context = LocalContext.current

    HomeCard(
        onClick = { context.startActivity(content.intent) },
        modifier = Modifier.padding(8.dp),
    ) {
        Column(
            modifier = Modifier.absolutePadding(left = 16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = content.description)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeCard(
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(64.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp,
        content = content,
    )
}

@Preview
@Composable
fun previewHomeSuccess() {
    HomeRoute(
        contents = listOf(
            Content(
                intent = ChatRoomListActivity.getIntent(LocalContext.current),
                description = "Chat Room List",
            ),
            Content(
                intent = LifecycleTackerActivity.getIntent(LocalContext.current),
                description = "Lifecycle Tracker",
            ),
        ),
    )
}
