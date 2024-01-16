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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.oneapp.domain.model.CartProduct

@Composable
fun ShoppingRoute(
    vm: ShoppingViewModel = hiltViewModel(),
) {
    val cartProducts by vm.products.collectAsStateWithLifecycle()

    ShoppingScreen(
        cartProducts = cartProducts,
        onCountChanged = { id, count -> vm.updateCount(id, count) },
    )
}

@Composable
fun ShoppingScreen(
    cartProducts: List<CartProduct>,
    onCountChanged: (Long, Int) -> Unit = { _, _ -> },
) {
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
        cartProducts.forEachIndexed { idx, cartProduct ->
            item {
                ShoppingItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(8.dp)
                        .clickable { },
                    product = cartProduct.product,
                    count = idx,
                    onItemClick = {
                        Toast.makeText(context, "Clicked $it", Toast.LENGTH_SHORT).show()
                    },
                    onCountChanged = onCountChanged,
                )
            }
        }
    }
}

@Composable
@Preview
fun ShoppingRootPreview() {
    ShoppingRoute()
}
