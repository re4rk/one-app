package com.re4rk.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.re4rk.presentation.ui.chatRoomList.ChatRoomListActivity
import com.re4rk.presentation.ui.lifecycleTracker.LifecycleTackerActivity
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun HomeRoute(vm: HomeViewModel = hiltViewModel()) {
    HomeScreen(vm.getHomeScreenContents(context = LocalContext.current))
}

@Composable
private fun HomeScreen(contents: List<Content>) = OneAppTheme(
    dynamicColor = false,
) {
    HomeScreenContent(contents)
}

@Composable
private fun HomeScreenContent(contents: List<Content>) {
    Column {
        Text(
            text = "SandBox",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(16.dp),
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp),
                )
                .padding(8.dp)
                .height(400.dp),
            columns = GridCells.Adaptive(minSize = 128.dp),
        ) {
            contents.forEach { content ->
                item {
                    CategoryItem(content = content)
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    modifier: Modifier = Modifier,
    content: Content,
) {
    val context = LocalContext.current

    HomeCard(
        onClick = { context.startActivity(content.intent) },
        modifier = modifier.padding(8.dp),
    ) {
        Column(
            modifier = modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = content.description,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(96.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 4.dp,
        content = content,
    )
}

@Preview
@Composable
fun PreviewHomeSuccess() {
    HomeScreen(
        contents = listOf(
            Content(
                intent = ChatRoomListActivity.getIntent(LocalContext.current),
                description = "Chat Room List",
            ),
            Content(
                intent = LifecycleTackerActivity.getIntent(LocalContext.current),
                description = "Lifecycle Tracker",
            ),
            Content(
                intent = LifecycleTackerActivity.getIntent(LocalContext.current),
                description = "Lifecycle Tracker",
            ),
            Content(
                intent = LifecycleTackerActivity.getIntent(LocalContext.current),
                description = "Lifecycle Tracker",
            ),
        ),
    )
}

@Preview
@Composable
fun PreviewCategoryItem() {
    CategoryItem(
        modifier = Modifier.padding(8.dp),
        content = Content(
            intent = ChatRoomListActivity.getIntent(LocalContext.current),
            description = "Chat Room List",
        ),
    )
}
