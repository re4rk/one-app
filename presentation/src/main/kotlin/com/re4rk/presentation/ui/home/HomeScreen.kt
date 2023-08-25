package com.re4rk.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun HomeRoot(contents: List<Content>) {
    Screen(contents)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(contents: List<Content>) {
    val context = LocalContext.current
    OneAppTheme {
        Scaffold(
            modifier = Modifier
                .width(360.dp)
                .fillMaxHeight(),
            content = {
                Column {
                    contents.forEach { content ->
                        Box(
                            modifier = Modifier
                                .width(360.dp)
                                .clickable { context.startActivity(content.intent) },
                        ) {
                            Text(text = content.description)
                        }
                    }
                }
            },
        )
    }
}

@Preview
@Composable
fun previewHomeRoot() {
    HomeRoot(
        contents = List(10) {
            Content(
                intent = ChatRoomListActivity.getIntent(LocalContext.current),
                description = "Chat Room List",
            )
        },
    )
}
