package com.re4rk.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun HomeRoot(contents: List<Content>) {
    Screen(contents)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(contents: List<Content>) {
    OneAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxHeight(),
            content = {
                Column {
                    contents.forEach { content -> CategoryItem(content) }
                }
            },
        )
    }
}

@Composable
private fun CategoryItem(
    content: Content,
) {
    val context = LocalContext.current

    Card(
        onClick = { context.startActivity(content.intent) },
        modifier = Modifier.padding(4.dp),
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = content.description,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Card(
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFE0E0E0),
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        color = color,
        shape = RoundedCornerShape(32.dp),
        shadowElevation = 2.dp,
        content = content,
    )
}

@Preview
@Composable
fun previewHomeRoot() {
    HomeRoot(
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
