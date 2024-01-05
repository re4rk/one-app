package com.re4rk.presentation.ui.lifecycleTracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.presentation.ui.theme.OneAppTheme
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LifecycleTrackerRoot(text: StateFlow<List<String>>, lifecycle: Lifecycle) {
    val messageBuffer = text.collectAsStateWithLifecycle(
        lifecycle = lifecycle,
        minActiveState = Lifecycle.State.STARTED,
    )
    Screen(text = messageBuffer.value)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Screen(text: List<String>) {
    OneAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                Modifier.fillMaxSize(),
            ) {
                val textState = remember {
                    mutableStateOf("")
                }
                Text(text = text.joinToString())
                Text(text = textState.value)
                Spacer(modifier = Modifier.size(100.dp))

                TextField(
                    value = textState.value,
                    onValueChange = {
                        textState.value = it
                    },
                )
            }
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview2() {
    Screen(text = listOf("Hello", "World"))
}
