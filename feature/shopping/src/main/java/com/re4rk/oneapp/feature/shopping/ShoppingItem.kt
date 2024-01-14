package com.re4rk.oneapp.feature.shopping

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.oneapp.core.designsystem.component.DynamicAsyncImage
import com.re4rk.oneapp.core.designsystem.icon.ArkIcons

@Composable
fun ShoppingItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    price: String,
    description: String,
    count: Int,
    countChangeListener: (Int) -> Unit = {},
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true,
            )
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DynamicAsyncImage(
                imageUrl = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(8.dp),
            ) {
                ShoppingItemTitle(title)
                ShoppingItemPrice(price)
                ShoppingItemDescription(description)
            }
        }

        ShoppingItemCounter(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            count = count,
            countChangeListener = countChangeListener,
        )
    }
}

@Composable
fun ShoppingItemTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        fontSize = MaterialTheme.typography.titleMedium.fontSize,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun ShoppingItemPrice(price: String) {
    Text(
        text = price,
        modifier = Modifier.fillMaxWidth(),
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
    )
}

@Composable
fun ShoppingItemDescription(description: String) {
    Text(
        text = description,
        modifier = Modifier.fillMaxWidth(),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
    )
}

@Composable
fun ShoppingItemCounter(
    modifier: Modifier = Modifier,
    count: Int,
    countChangeListener: (Int) -> Unit = {},
) = Box(
    modifier = modifier,
    contentAlignment = Alignment.BottomEnd,
) {
    ShoppingItemCounterPlus(count = count, countChangeListener = countChangeListener)
    ShoppingItemCounterZero(count = count, countChangeListener = countChangeListener)
}

@Composable
fun ShoppingItemCounterZero(
    count: Int,
    countChangeListener: (Int) -> Unit,
) = AnimatedVisibility(
    visible = count <= 0,
    enter = fadeIn(initialAlpha = 0.5f),
    exit = fadeOut(),
) {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable { countChangeListener(1) },
    ) {
        Image(
            ArkIcons.ShoppingCart,
            contentDescription = null,
            modifier = Modifier.size(18.dp).align(Alignment.Center),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary),
        )
    }
}

@Composable
fun ShoppingItemCounterPlus(
    count: Int,
    countChangeListener: (Int) -> Unit,
) = AnimatedVisibility(
    visible = count > 0,
    enter = expandHorizontally(initialWidth = { 0 }),
    exit = shrinkHorizontally(targetWidth = { 0 }),
) {
    Row(
        modifier = Modifier
            .width(96.dp)
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        val innerModifier = Modifier.weight(1f).fillMaxHeight()

        Box(modifier = innerModifier.clickable { countChangeListener(count - 1) }) {
            Text(
                text = "-",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Box(modifier = innerModifier) {
            Text(
                text = count.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
        Box(modifier = innerModifier.clickable { countChangeListener(count + 1) }) {
            Text(
                text = "+",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
@Preview
fun ShoppingItemCounterPreview() {
    Surface {
        Column {
            ShoppingItemCounter(count = 0)
            ShoppingItemCounter(count = 1)
        }
    }
}

@Composable
@Preview
fun ShoppingItemPreview1() {
    Surface {
        ShoppingItem(
            imageUrl = "https://picsum.photos/500/500",
            modifier = Modifier
                .width(200.dp)
                .height(350.dp)
                .padding(8.dp),
            title = "Title",
            price = "$100",
            description = "Description".repeat(60),
            count = 0,
        )
    }
}

@Composable
@Preview
fun ShoppingItemPreview2() {
    ShoppingItem(
        imageUrl = "https://picsum.photos/500/500",
        modifier = Modifier
            .width(400.dp)
            .height(350.dp)
            .padding(8.dp),
        title = "Title",
        price = "$100",
        description = "Description".repeat(60),
        count = 0,
    )
}
