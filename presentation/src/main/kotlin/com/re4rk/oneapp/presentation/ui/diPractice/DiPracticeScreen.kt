package com.re4rk.presentation.ui.diPractice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.domain.model.Practice
import com.re4rk.presentation.ui.theme.OneAppTheme

@Composable
fun DiPracticeRoot(vm: DiPracticeViewModel) {
    val practice: List<Practice> by vm.practices.collectAsStateWithLifecycle()

    Screen(practice = practice)
}

@Composable
private fun Screen(practice: List<Practice>) {
    OneAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn {
                practice.forEach { practice ->
                    item {
                        Greeting(practice)
                    }
                }
            }
        }
    }
}

@Composable
private fun Greeting(practice: Practice) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(4.dp),
        color = Color(0xFFE0E0E0),
    ) {
        Text(
            text = "Hello $practice!",
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Screen(practice = List(20) { Practice() })
}
