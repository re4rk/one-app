package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.oneapp.core.model.coinone.OrderBook

@Composable
fun OrderRoute(
    vm: OrderViewModel = hiltViewModel(),
) {
    val orderBookState = vm.orderBook.collectAsStateWithLifecycle()

    OrderScreen(
        orderBook = orderBookState.value.getOrNull(),
    )
}

@Composable
fun OrderScreen(
    orderBook: OrderBook?,
) {
    Text(text = "Hello, World!")
}

@Preview
@Composable
fun CoinoneOrderScreenPreview() {
    OrderScreen(
        OrderBook(
            timestamp = 0,
            bids = emptyList(),
            asks = emptyList(),
            errorCode = "",
            id = "",
            orderBookUnit = "",
            quoteCurrency = "",
            result = "",
            targetCurrency = "",
        ),
    )
}
