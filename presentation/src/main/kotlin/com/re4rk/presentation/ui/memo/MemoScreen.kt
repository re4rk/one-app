package com.re4rk.presentation.ui.memo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.domain.model.Memo
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun MemoScreen(vm: MemoViewModel) {
    val memos: List<Memo> by vm.memo.collectAsStateWithLifecycle()

    Screen(memos = memos)
}

@Composable
private fun Screen(memos: List<Memo>) {
    OneAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn {
                memos.forEach { memo ->
                    item {
                        Card(memo = memo)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Card(
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFE0E0E0),
    memo: Memo,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp,
            ),
        color = color,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 2.dp,
        content = {
            Column {
                Text(
                    text = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello ${memo.title}!",
                    modifier = Modifier
                        .padding(12.dp),
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineSmall,

                    overflow = TextOverflow.Ellipsis,
                )

                Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))

                Box(modifier = Modifier.wrapContentHeight()) {
                    Text(
                        text = "HeaaaaaaaaaaaaaaaaaaaaaaaalloHeaaaaaaaaaaaaaaaaaaaaaaaallo ${memo.content}!",
                        modifier = Modifier
                            .padding(12.dp)
                            .wrapContentWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Screen(
        memos = List(20) {
            Memo(
                id = it,
                title = "Hello $it",
                content = "Hello $it",
            )
        },
    )
}
