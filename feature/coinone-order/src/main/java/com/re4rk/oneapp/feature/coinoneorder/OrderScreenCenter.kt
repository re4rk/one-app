package com.re4rk.oneapp.feature.coinoneorder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.re4rk.oneapp.core.designsystem.icon.ArkIcons
import com.re4rk.oneapp.core.designsystem.theme.CoinoneTheme
import com.re4rk.oneapp.core.model.coinone.Ask
import com.re4rk.oneapp.core.model.coinone.Bid
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker
import com.re4rk.oneapp.feature.coinoneorder.designsystem.PriceBox

@Composable
internal fun ColumnScope.OrderScreenCenter(
    uiState: OrderScreenUiState,
    orderBook: OrderBook,
    ticker: Ticker,
    onTradeModeChange: (TradeMode) -> Unit,
    onTradeWayChange: (TradeWay) -> Unit,
) {
    Row(Modifier.weight(1f)) {
        LazyColumn(
            Modifier
                .weight(0.448f)
                .fillMaxHeight(),
            state = rememberLazyListState(
                initialFirstVisibleItemIndex = orderBook.asks.size - 8,
            ),
        ) {
            for (ask in orderBook.asks.reversed()) {
                item { OrderAskBox(modifier = Modifier.height(40.dp).fillMaxWidth(), ask, ticker) }
            }
            for (bid in orderBook.bids) {
                item { OrderBidBox(modifier = Modifier.height(40.dp).fillMaxWidth(), bid, ticker) }
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(0.552f)
                .fillMaxHeight(),
        ) {
            Spacer(modifier = Modifier.padding(4.dp))

            CheckButtons(
                currentTradeMode = uiState.tradeMode,
                onTradeModeChange = onTradeModeChange,
            )

            Spacer(modifier = Modifier.padding(4.dp))

            RadioButtons(
                currentTradeWay = uiState.tradeWay,
                onTradeWayChange = onTradeWayChange,
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PriceBox(
                    modifier = Modifier.weight(1f),
                    prefix = "주문가능",
                    price = "-",
                    suffix = "KRW",
                    color = Color(0x00000000),
                )
                Image(ArkIcons.Add, contentDescription = null)
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PriceBox(
                    Modifier.weight(1f),
                    prefix = "가격",
                    price = "56,912,000",
                )
                OrderLinkedOutlinedButtons(
                    modifier = Modifier
                        .height(36.dp)
                        .width(52.dp),
                    list = listOf(ArkIcons.Remove, ArkIcons.Add),
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))

            PriceBox(prefix = "수량", price = "0", suffix = "BTC")

            Spacer(modifier = Modifier.padding(20.dp))

            PriceBox(prefix = "총액", price = "0", suffix = "KRW")

            TextBox(text = "로그인", color = uiState.tradeMode.color)

            Spacer(modifier = Modifier.weight(1f))

            SubTextMiddle(text = "수수료율 0.2%")
            Row {
                SubTextMiddle(text = "최소주문금액 5,000 KRW")
                Spacer(modifier = Modifier.weight(1f))
                SubTextMiddle(text = "주문 유형")
            }
        }
    }
}

@Composable
fun CheckButtons(
    modifier: Modifier = Modifier,
    currentTradeMode: TradeMode,
    onTradeModeChange: (TradeMode) -> Unit,
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .height(36.dp)
        .border(
            1.dp,
            Color(0xFFE0E0E0),
            shape = RoundedCornerShape(8.dp),
        ),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    TradeMode.getAll().forEach { state ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(4.dp)
                .background(
                    color = if (state == currentTradeMode) {
                        currentTradeMode.backgroundColor
                    } else {
                        Color(0xFFE0E0E0)
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(4.dp),
                )
                .clickable { onTradeModeChange(state) },
        ) {
            Text(
                text = state.text,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                color = if (state == currentTradeMode) {
                    currentTradeMode.color
                } else {
                    Color(0xFFBDBDBD)
                },
            )
        }
    }
}

@Composable
fun RadioButtons(
    modifier: Modifier = Modifier,
    currentTradeWay: TradeWay,
    onTradeWayChange: (TradeWay) -> Unit,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
) {
    TradeWay.getAll().forEach { tradeWay ->
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = currentTradeWay == tradeWay,
                modifier = Modifier.size(20.dp),
                onClick = { onTradeWayChange(tradeWay) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color(0xFF1772F8),
                    unselectedColor = Color(0xFFC9CCD2),
                ),
            )
            Text(
                text = tradeWay.text,
                modifier = Modifier
                    .wrapContentWidth(),
                color = if (currentTradeWay == tradeWay) {
                    Color(0xFF18191C)
                } else {
                    Color(0xFFBDBDBD)
                },
            )
        }
    }
}

@Composable
fun OrderLinkedOutlinedButtons(
    modifier: Modifier = Modifier,
    list: List<ImageVector>,
    round: Dp = 4.dp,
) = Row(
    modifier,
    verticalAlignment = Alignment.CenterVertically,
) {
    list.forEachIndexed { index, it ->
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxHeight()
                .offset((-index).dp)
                .weight(1f),
            contentPadding = PaddingValues(0.dp),
            shape = when {
                index == 0 -> RoundedCornerShape(round, 0.dp, 0.dp, round)
                index < list.size - 1 -> RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                else -> RoundedCornerShape(0.dp, round, round, 0.dp)
            },
            border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
        ) {
            Image(
                imageVector = it,
                colorFilter = ColorFilter.tint(Color(0xFFBDBDBD)),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun TextBox(
    text: String,
    color: Color,
) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(36.dp)
        .background(
            color = color,
            shape = RoundedCornerShape(8.dp),
        ),
) {
    Text(
        text = text,
        modifier = Modifier.align(Alignment.Center),
        color = Color.White,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun SubTextMiddle(
    text: String,
    modifier: Modifier = Modifier,
) = Text(
    text = text,
    modifier = modifier,
    color = Color(0xFFBDBDBD),
    fontSize = 11.sp,
)

@Composable
internal fun OrderBidBox(
    modifier: Modifier = Modifier,
    bid: Bid,
    ticker: Ticker,
) {
    val yesterdayLast = (ticker.yesterdayLast ?: "").toDouble()
    val fluctuation = (bid.price.toDouble() - yesterdayLast) / yesterdayLast * 100
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFEF7F8))
                .weight(0.6f)
                .fillMaxHeight(),

            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = bid.price.reversed().chunked(3).joinToString(",").reversed(),
                fontSize = 12.sp,
                color = CoinoneTheme.colorScheme.primary,
            )
            Text(
                text = "%,.2f%%".format(fluctuation),
                fontSize = 10.sp,
                color = CoinoneTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFFEF7F8))
                .weight(0.4f)
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
    modifier: Modifier = Modifier,
    ask: Ask,
    ticker: Ticker,
) {
    val yesterdayLast = (ticker.yesterdayLast ?: "").toDouble()
    val fluctuation = (ask.price.toDouble() - yesterdayLast) / yesterdayLast * 100
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(Color(0xFFF6FAFF))
                .weight(0.6f)
                .fillMaxHeight(),

            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = ask.price.reversed().chunked(3).joinToString(",").reversed(),
                fontSize = 12.sp,
                color = CoinoneTheme.colorScheme.primary,
            )
            Text(
                text = "%,.2f%%".format(fluctuation),
                fontSize = 10.sp,
                color = CoinoneTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.padding(1.dp))
        Column(
            modifier = Modifier
                .background(Color(0xFFF6FAFF))
                .weight(0.4f)
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
    var orderScreenUiState = OrderScreenUiState()
    Surface {
        Column {
            OrderScreenCenter(
                uiState = OrderScreenUiState(),
                orderBook = FakeObject.orderBook,
                ticker = FakeObject.ticker,
                onTradeModeChange = {
                    orderScreenUiState = orderScreenUiState.copy(tradeMode = it)
                },
                onTradeWayChange = {},
            )
        }
    }
}
