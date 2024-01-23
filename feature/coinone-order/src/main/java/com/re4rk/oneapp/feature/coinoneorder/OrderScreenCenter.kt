package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.re4rk.oneapp.core.model.coinone.Ask
import com.re4rk.oneapp.core.model.coinone.Bid
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker

@Composable
internal fun ColumnScope.OrderScreenCenter(
    orderBook: OrderBook,
    ticker: Ticker,
) {
    Row(Modifier.weight(1f)) {
        LazyColumn(
            Modifier.width(191.dp),
            state = rememberLazyListState(
                initialFirstVisibleItemIndex = orderBook.asks.size - 8,
            ),
        ) {
            for (ask in orderBook.asks.reversed()) {
                item { OrderAskBox(ask, ticker) }
            }
            for (bid in orderBook.bids) {
                item { OrderBidBox(bid, ticker) }
            }
        }
        Box(
            modifier = Modifier
                .padding(1.dp)
                .background(Color(0x11111111))
                .fillMaxSize(),
        )
    }
}

@Composable
internal fun OrderBidBox(
    bid: Bid,
    ticker: Ticker,
) {
    val yesterdayLast = (ticker.yesterdayLast ?: "").toDouble()
    val fluctuation = (bid.price.toDouble() - yesterdayLast) / yesterdayLast * 100
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
                text = "%,.2f%%".format(fluctuation),
                fontSize = 10.sp,
                color = Color(0xFF1763B6),
            )
        }
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
internal fun OrderAskBox(
    ask: Ask,
    ticker: Ticker,
) {
    val yesterdayLast = (ticker.yesterdayLast ?: "").toDouble()
    val fluctuation = (ask.price.toDouble() - yesterdayLast) / yesterdayLast * 100
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
                text = "%,.2f%%".format(fluctuation),
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

@Preview(heightDp = 600, widthDp = 428)
@Composable
internal fun CoinoneOrderScreenCenterPreview() {
    Surface {
        Column {
            OrderScreenCenter(
                orderBook = FakeObject.orderBook,
                ticker = FakeObject.ticker,
            )
        }
    }
}
