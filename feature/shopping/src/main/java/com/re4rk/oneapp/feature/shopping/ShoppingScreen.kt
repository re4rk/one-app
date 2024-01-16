package com.re4rk.oneapp.feature.shopping

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.oneapp.domain.model.CartProduct
import com.re4rk.oneapp.domain.model.Product

@Composable
fun ShoppingRoute(
    onItemClick: (Long) -> Unit = { },
    vm: ShoppingViewModel = hiltViewModel(),
) {
    val cartProducts by vm.products.collectAsStateWithLifecycle()

    ShoppingScreen(
        cartProducts = cartProducts,
        onItemClick = onItemClick,
        onCountChanged = { id, count -> vm.updateCount(id, count) },
    )
}

@Composable
fun ShoppingScreen(
    cartProducts: List<CartProduct>,
    onItemClick: (Long) -> Unit = { },
    onCountChanged: (Long, Int) -> Unit = { _, _ -> },
) {
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
        cartProducts.forEach { cartProduct ->
            item {
                ShoppingItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp)
                        .clickable { },
                    product = cartProduct.product,
                    count = cartProduct.count,
                    onItemClick = onItemClick,
                    onCountChanged = onCountChanged,
                )
            }
        }
    }
}

@Composable
@Preview
fun ShoppingScreenPreview() {
    ShoppingScreen(
        cartProducts = listOf(
            CartProduct(
                product = Product(
                    id = 4,
                    title = "Product 4",
                    description = "Description 4",
                    price = "400",
                    imageUrl = "https://picsum.photos/200/300",
                ),
                count = 4,
            ),
            CartProduct(
                product = Product(
                    id = 5,
                    title = "Product 5",
                    description = "Description 5",
                    price = "500",
                    imageUrl = "https://picsum.photos/200/300",
                ),
                count = 5,
            ),
            CartProduct(
                product = Product(
                    id = 6,
                    title = "Product 6",
                    description = "Description 6",
                    price = "600",
                    imageUrl = "https://picsum.photos/200/300",
                ),
                count = 6,
            ),
        ),
    )
}
