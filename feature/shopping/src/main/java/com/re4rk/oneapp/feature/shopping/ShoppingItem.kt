package com.re4rk.oneapp.feature.shopping

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.oneapp.core.designsystem.component.DynamicAsyncImage

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
        modifier =
        modifier
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DynamicAsyncImage(
                imageUrl = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
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
) {
    Box(
        modifier = modifier,
    ) {
        if (count > 0) {
            ShoppingItemCounterPlus(count = count, countChangeListener = countChangeListener)
        } else {
            ShoppingItemCounterZero(countChangeListener = countChangeListener)
        }
    }
}

@Composable
fun ShoppingItemCounterZero(
    countChangeListener: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .width(32.dp)
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable { countChangeListener(1) },
    ) {
        Text(
            text = "+",
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun ShoppingItemCounterPlus(
    count: Int,
    countChangeListener: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(32.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Box(
            modifier = Modifier
                .width(32.dp)
                .fillMaxHeight()
                .clickable { countChangeListener(count - 1) },
        ) {
            Text(
                text = "-",
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Box(
            modifier = Modifier
                .width(32.dp)
                .fillMaxHeight(),
        ) {
            Text(
                text = count.toString(),
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Box(
            modifier = Modifier
                .width(32.dp)
                .fillMaxHeight()
                .clickable { countChangeListener(count + 1) },
        ) {
            Text(
                text = "+",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
@Preview(showBackground = false)
fun ShoppingItemCounterPreview() {
    Column {
        ShoppingItemCounter(count = 0)
        ShoppingItemCounter(count = 1)
    }
}

@Composable
@Preview(showBackground = false)
fun ShoppingItemPreview1() {
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

@Composable
@Preview(showBackground = false)
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
