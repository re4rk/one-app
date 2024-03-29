package com.re4rk.oneapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Error
import coil.compose.AsyncImagePainter.State.Loading
import coil.compose.rememberAsyncImagePainter
import com.re4rk.oneapp.core.designsystem.R
import com.re4rk.oneapp.core.designsystem.theme.LocalTintTheme

@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter = painterResource(R.drawable.ic_placeholder_default),
    contentScale: ContentScale = ContentScale.FillBounds,
) {
    val iconTint = LocalTintTheme.current.iconTint
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            isLoading = state is Loading
            isError = state is Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading && !isLocalInspection) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
        Image(
            painter = if (isError.not() && !isLocalInspection) imageLoader else placeholder,
            contentDescription = contentDescription,
            colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Preview
@Composable
fun PreviewDynamicAsyncImage50x150() {
    DynamicAsyncImage(
        imageUrl = "https:empty",
        contentDescription = "Dynamic Async Image",
        modifier = Modifier.size(width = 50.dp, height = 150.dp),
    )
}

@Preview
@Composable
fun PreviewDynamicAsyncImage100x100() {
    DynamicAsyncImage(
        imageUrl = "https:empty",
        contentDescription = "Dynamic Async Image",
        modifier = Modifier.size(100.dp),
    )
}

@Preview
@Composable
fun PreviewDynamicAsyncImage150x50() {
    DynamicAsyncImage(
        imageUrl = "https:empty",
        contentDescription = "Dynamic Async Image",
        modifier = Modifier.size(width = 150.dp, height = 50.dp),
    )
}
