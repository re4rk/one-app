package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.re4rk.oneapp.core.designsystem.theme.CoinoneTheme
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker
import kotlin.math.roundToInt

@Composable
fun OrderRoute(
    vm: OrderViewModel = hiltViewModel(),
) {
    val orderBookState = vm.orderBook.collectAsStateWithLifecycle()
    val tickerState = vm.ticker.collectAsStateWithLifecycle()

    val uiState = remember { mutableStateOf(OrderScreenUiState()) }

    when {
        orderBookState.value.isFailure -> {
            Text(
                text = orderBookState.value.exceptionOrNull()?.message ?: "unknown error",
                modifier = Modifier.padding(16.dp),
            )
        }

        tickerState.value.isFailure -> {
            Text(
                text = tickerState.value.exceptionOrNull()?.message ?: "unknown error",
                modifier = Modifier.padding(16.dp),
            )
        }

        else -> {
            OrderScreen(
                uiState = uiState.value,
                orderBook = orderBookState.value.getOrThrow(),
                ticker = tickerState.value.getOrThrow(),
                onTradeModeChange = { uiState.value = uiState.value.copy(tradeMode = it) },
                onTradeWayChange = { uiState.value = uiState.value.copy(tradeWay = it) },
            )
        }
    }
}

@Composable
fun OrderScreen(
    uiState: OrderScreenUiState = OrderScreenUiState(),
    orderBook: OrderBook,
    ticker: Ticker,
    onTradeModeChange: (TradeMode) -> Unit = {},
    onTradeWayChange: (TradeWay) -> Unit = {},
) = Column {
    OrderScreenTop(ticker)

    OrderScreenCenter(uiState, orderBook, ticker, onTradeModeChange, onTradeWayChange)

    OrderScreenBottom()
}

@Composable
private fun OrderScreenTop(
    ticker: Ticker,
) {
    val last = ticker.last.toDouble()
    val yesterdayLast = ticker.yesterdayLast!!.toDouble()
    val dailyFluctuation = last - yesterdayLast
    val dailyFluctuationRate = (dailyFluctuation) / yesterdayLast * 10000.0
    val yesterdayHigh = (ticker.yesterdayHigh ?: "").toDouble().toLong()
    val yesterdayLow = (ticker.yesterdayLow ?: "").toDouble().toLong()

    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = "%,d".format(ticker.last.toDouble().toLong()),
            style = CoinoneTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Column {
            Text(
                text = "%,d".format(dailyFluctuation.toLong()),
                color = CoinoneTheme.colorScheme.primary,
                fontSize = 10.sp,
            )
            Text(
                text = "%,.2f%%".format(dailyFluctuationRate.roundToInt().toDouble() / 100),
                color = Color(0xFF1763B6),
                fontSize = 11.sp,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.End,
        ) {
            Text(
                text = "24H 고가",
                color = Color(0xFFAEB3BB),
                fontSize = 8.sp,
            )
            Text(
                text = "%,d".format(yesterdayHigh.toDouble().toLong()),
                color = Color(0xFF484D55),
                fontSize = 8.sp,
            )
            Text(
                text = "24H 저가",
                color = Color(0xFFAEB3BB),
                fontSize = 8.sp,
            )
            Text(
                text = "%,d".format(yesterdayLow.toDouble().toLong()),
                color = Color(0xFF484D55),
                fontSize = 8.sp,
            )
        }

        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(Color(0xFF1763B6))
                .size(width = 114.dp, height = 64.dp),
        ) {
        }
    }
    val selected = remember { mutableStateOf(0) }
    val tabs = listOf("주문", "호가", "차트", "시세", "정보")

    TabRow(
        selectedTabIndex = selected.value,
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selected.value])
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp)) // clip modifier not working
                    .padding(horizontal = 28.dp)
                    .background(color = Color.Black),
            )
        },
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = {
                    Text(
                        text = tab,
                        style = MaterialTheme.typography.labelLarge,
                        fontSize = 14.sp,
                        color = if (index == selected.value) Color(0xff000000) else Color(0xFFAEB3BB),
                    )
                },
                selected = index == selected.value,
                onClick = { selected.value = index },
            )
        }
    }
}

@Composable
private fun OrderScreenBottom() {
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

@Preview(heightDp = 926, widthDp = 428)
@Composable
fun CoinoneOrderScreenPreview() {
    Surface {
        OrderScreen(
            orderBook = FakeObject.orderBook,
            ticker = FakeObject.ticker,
        )
    }
}
