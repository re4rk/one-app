package com.re4rk.presentation.ui.memo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.domain.model.Memo
import com.re4rk.presentation.R
import com.re4rk.presentation.designSystem.component.ArkTopAppBar
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun MemoScreen(
    onNavigationClick: () -> Unit = { },
    onActionClick: () -> Unit = { },
    onSubmit: (String) -> Unit = { },
    vm: MemoViewModel,
) {
    val memos: List<Memo> by vm.memo.collectAsStateWithLifecycle()

    Screen(
        onNavigationClick = onNavigationClick,
        onActionClick = onActionClick,
        onSubmit = onSubmit,
        memos = memos,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit,
    onSubmit: (String) -> Unit,
    memos: List<Memo>,
) {
    OneAppTheme {
        Scaffold(
            topBar = {
                ArkTopAppBar(
                    titleRes = R.string.app_name,
                    navigationIcon = Icons.Default.ArrowBack,
                    navigationIconContentDescription = null,
                    actionIcon = Icons.Default.Menu,
                    actionIconContentDescription = null,
                    onNavigationClick = onNavigationClick,
                    onActionClick = onActionClick,
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                ) {
                    val text = remember { mutableStateOf("") }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        value = text.value,
                        onValueChange = {
                            text.value = it
                            onSubmit(it)
                        },
                    )
                }
            },
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background,
            ) {
                LazyColumn {
                    memos.forEach { memo ->
                        item {
                            MemoCard(memo = memo)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MemoCard(
    onClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    memo: Memo,
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
            Text(
                text = memo.title,
                modifier = Modifier
                    .padding(12.dp),
                maxLines = 1,
                style = MaterialTheme.typography.headlineSmall,

                overflow = TextOverflow.Ellipsis,
            )

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
    Screen(
        onActionClick = {},
        onNavigationClick = {},
        onSubmit = {},
        memos = List(20) {
            Memo(
                id = it,
                title = "Hello $it",
                content = "Hello $it",
            )
        },
    )
}
