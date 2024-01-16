package com.re4rk.oneapp.feature.shoppingdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.oneapp.core.designsystem.component.DynamicAsyncImage
import com.re4rk.oneapp.domain.model.Product

@Composable
fun ShoppingDetailScreen(
    product: Product,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        DynamicAsyncImage(
            imageUrl = product.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp),
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
@Preview
fun ShoppingDetailScreenPreview() {
    Surface {
        ShoppingDetailScreen(
            product = Product(
                id = 1,
                title = "name",
                description = "description",
                price = "100",
                imageUrl = "https://picsum.photos/600/600",
            ),
        )
    }
}
