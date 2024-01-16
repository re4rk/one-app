package com.re4rk.oneapp.feature.shopping

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShoppingRoot() {
    ShoppingScreen()
}

@Composable
fun ShoppingScreen() {
    val context = LocalContext.current

    LazyVerticalGrid(
        modifier =
        Modifier
            .background(
                MaterialTheme.colorScheme.surface,
            )
            .padding(8.dp)
            .fillMaxHeight(),
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        repeat(10) {
            item {
                val count = remember { mutableStateOf(2) }
                ShoppingItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp)
                        .clickable { },
                    imageUrl = "https://picsum.photos/600/${600 + it}",
                    id = it,
                    title = "Title",
                    price = "Price",
                    description = "Description",
                    count = count.value,
                    onItemClick = {
                        Toast.makeText(context, "Clicked $it", Toast.LENGTH_SHORT).show()
                    },
                    onCountChanged = { count.value = it },
                )
            }
        }
    }
}

@Composable
@Preview
fun ShoppingRootPreview() {
    ShoppingRoot()
}
