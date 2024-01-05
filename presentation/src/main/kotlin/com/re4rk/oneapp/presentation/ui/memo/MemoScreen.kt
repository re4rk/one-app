package com.re4rk.presentation.ui.memo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.domain.model.Memo

@Composable
fun MemoRoute(
    vm: MemoViewModel = hiltViewModel(),
) {
    val memos: List<Memo> by vm.memo.collectAsStateWithLifecycle()

    MemoScreen(
        onSubmit = { vm.addMemo(it) },
        onDelete = { vm.deleteMemo(it) },
        memos = memos,
    )
}

@Composable
private fun MemoScreen(
    onSubmit: (String) -> Unit,
    onDelete: (Int) -> Unit,
    memos: List<Memo>,
) {
    LazyColumn {
        memos.forEach { memo ->
            item {
                MemoCard(
                    memo = memo,
                    onDelete = onDelete,
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                val text = remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    value = text.value,
                    onValueChange = {
                        text.value = it
                    },
                )

                Button(
                    onClick = { onSubmit(text.value) },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(8.dp),
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MemoCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    memo: Memo,
    onDelete: (Int) -> Unit = { },
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp,
            ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(12.dp),
            ) {
                Text(
                    text = memo.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.headlineSmall,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f),
                )
                Image(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = modifier.clickable { onDelete(memo.id) },
                )
            }

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 8.dp),
            )

            Box(modifier = Modifier.wrapContentHeight()) {
                Text(
                    text = memo.content,
                    modifier = Modifier
                        .padding(12.dp)
                        .wrapContentWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoScreen(
        onSubmit = {},
        onDelete = {},
        memos = List(20) {
            Memo(
                id = it,
                title = "Hello $it",
                content = "Hello $it",
            )
        },
    )
}
