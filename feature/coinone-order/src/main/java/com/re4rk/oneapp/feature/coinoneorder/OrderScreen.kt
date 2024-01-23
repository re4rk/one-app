package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.oneapp.core.model.coinone.Ask
import com.re4rk.oneapp.core.model.coinone.Bid
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker

@Composable
fun OrderRoute(
    vm: OrderViewModel = hiltViewModel(),
) {
    val orderBookState = vm.orderBook.collectAsStateWithLifecycle()
    val tickerState = vm.ticker.collectAsStateWithLifecycle()

    orderBookState.value.onSuccess {
        OrderScreen(
            orderBook = orderBookState.value.getOrThrow(),
            ticker = tickerState.value.getOrThrow(),
        )
    }
}

@Composable
fun OrderScreen(
    orderBook: OrderBook,
    ticker: Ticker,
) {
    Box {
        Column {
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(Color(0x11111111))
                    .size(width = 428.dp, height = 90.dp),
            )
            Row(Modifier.weight(1f)) {
                LazyColumn(
                    Modifier.width(191.dp),
                ) {
                    for (ask in orderBook.asks.reversed()) {
                        item {
                            OrderAskBox(ask)
                        }
                    }
                    for (bid in orderBook.bids) {
                        item {
                            OrderBidBox(bid)
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(1.dp)
                        .background(Color(0x11111111))
                        .fillMaxSize(),
                )
            }
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .background(Color(0x11111111))
                    .fillMaxWidth()
                    .height(height = 80.dp),
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart),
                ) {
                    Text(
                        text = "미체결",
                        style = MaterialTheme.typography.labelLarge,

                    )
                    Text(
                        text = "체결",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                ) {
                    Text(
                        text = "최근 체결",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Composable
fun OrderBidBox(
    bid: Bid,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFEF7F8))
                .width(110.dp)
                .fillMaxHeight(),

            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = bid.price.reversed().chunked(3).joinToString(",").reversed(),
                fontSize = 12.sp,
                color = Color(0xFF1763B6),
            )
            Text(
                text = "-0.51%",
                fontSize = 10.sp,
                color = Color(0xFF1763B6),
            )
        }
        // 254 247 248
        // 0x 0xF6 0xF7 0xF8
        Spacer(modifier = Modifier.padding(1.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFFEF7F8))
                .width(80.dp)
                .height(36.dp)
                .padding(vertical = 4.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = bid.qty,
                fontSize = 10.sp,
            )
        }
    }
    Spacer(modifier = Modifier.padding(1.dp))
}

@Composable
fun OrderAskBox(
    ask: Ask,
) {
    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(40.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFF6FAFF))
                .width(110.dp)
                .fillMaxHeight(),

            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = ask.price.reversed().chunked(3).joinToString(",").reversed(),
                fontSize = 12.sp,
                color = Color(0xFF1763B6),
            )
            Text(
                text = "-0.51%",
                fontSize = 10.sp,
                color = Color(0xFF1763B6),
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFF6FAFF))
                .width(80.dp)
                .fillMaxHeight()
                .padding(vertical = 4.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = ask.qty,
                fontSize = 10.sp,
            )
        }
    }
    Spacer(modifier = Modifier.padding(1.dp))
}

@Preview(heightDp = 926, widthDp = 428)
@Composable
fun CoinoneOrderScreenPreview() {
    Surface {
        OrderScreen(
            OrderBook(
                timestamp = 0,
                bids = listOf(
                    Bid("58600000", "0.06182624"),
                    Bid("58599000", "0.06182624"),
                    Bid("58597000", "0.06182624"),
                    Bid("58596000", "0.06182624"),
                    Bid("58594000", "0.06182624"),
                    Bid("58593000", "0.0935"),
                    Bid("58590000", "0.1737"),
                    Bid("58591000", "0.21987599"),
                ),
                asks = listOf(
                    Ask("58578000", "0.05851352"),
                    Ask("58572000", "0.0629082"),
                    Ask("58570000", "0.1727"),
                    Ask("58569000", "0.2119941"),
                    Ask("58568000", "0.08053185"),
                    Ask("58567000", "0.1257"),
                    Ask("58566000", "0.1257"),
                    Ask("58565000", "0.1257"),
                ),
                errorCode = "0",
                id = "1705569058746001",
                orderBookUnit = "0.0",
                quoteCurrency = "KRW",
                result = "success",
                targetCurrency = "BTC",
            ),
            Ticker(
                quoteCurrency = "krw",
                targetCurrency = "btc",
                timestamp = 1634169600,
                yesterdayFirst = "58600000",
                yesterdayHigh = "58600000",
                yesterdayLast = "58600000",
                yesterdayLow = "58600000",
                yesterdayQuoteVolume = "0.0",
                yesterdayTargetVolume = "0.0",
                bestAsks = listOf(Ask("58678000", "0.05851352")),
                bestBids = listOf(Bid("58500000", "0.06182624")),
                first = "58600000",
                high = "58600000",
                last = "58600000",
                low = "58600000",
                quoteVolume = "0.0",
                targetVolume = "0.0",
                id = "1705569058746001",
            ),
        )
    }
}
