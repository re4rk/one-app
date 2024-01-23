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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.re4rk.oneapp.core.designsystem.icon.ArkIcons
import com.re4rk.oneapp.core.model.coinone.Ask
import com.re4rk.oneapp.core.model.coinone.Bid
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker

sealed class TradeState(
    val textColor: Color,
    val containerColor: Color,
) {
    object Buy : TradeState(
        textColor = Color(0xFFE0274F),
        containerColor = Color(0xFFFCE9ED),
    )

    object Sell : TradeState(
        textColor = Color(0xFF1763B6),
        containerColor = Color(0xFFE8EFF8),
    )
}

@Composable
internal fun ColumnScope.OrderScreenCenter(
    orderBook: OrderBook,
    ticker: Ticker,
    tradeState: MutableState<TradeState> = remember { mutableStateOf(TradeState.Buy) },
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
        Column(
            modifier = Modifier
                .padding(1.dp)
                .fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
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
                val selected = remember { mutableStateOf(0) }
                val modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(4.dp)

                listOf("매수" to TradeState.Buy::class, "매도" to TradeState.Sell::class)
                    .forEach { (title, state) ->
                        Box(
                            modifier = modifier
                                .run {
                                    if (state == tradeState.value::class) {
                                        background(
                                            color = tradeState.value.containerColor,
                                            shape = RoundedCornerShape(4.dp),
                                        )
                                    } else {
                                        this
                                    }
                                }
                                .clickable {
                                    tradeState.value = when (tradeState.value) {
                                        is TradeState.Buy -> TradeState.Sell
                                        is TradeState.Sell -> TradeState.Buy
                                    }
                                },
                        ) {
                            Text(
                                text = title,
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = TextAlign.Center,
                                color = if (state == tradeState.value::class) {
                                    tradeState.value.textColor
                                } else {
                                    Color(0xFFBDBDBD)
                                },
                            )
                        }
                    }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            val selectedTradingWay = remember { mutableStateOf(0) }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                listOf("지정가", "시장가", "예약가").forEachIndexed { index, text ->
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedTradingWay.value == index,
                            modifier = Modifier.size(20.dp),
                            onClick = { selectedTradingWay.value = index },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF1772F8),
                                unselectedColor = Color(0xFFC9CCD2),
                            ),
                        )
                        Text(
                            text = text,
                            modifier = Modifier
                                .wrapContentWidth(),
                            color = if (selectedTradingWay.value == index) {
                                Color(0xFF18191C)
                            } else {
                                Color(
                                    0xFFBDBDBD,
                                )
                            },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "주문가능",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFBDBDBD),
                )
                Text(
                    text = "-",
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF18191C),
                    textAlign = TextAlign.End,
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "KRW",
                    modifier = Modifier,
                    color = Color(0xFFBDBDBD),
                )
                Image(ArkIcons.Add, contentDescription = null)
            }
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OrderPriceBox(Modifier.weight(1f))

                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .offset(0.dp, 0.dp)
                        .height(36.dp)
                        .width(26.dp),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 0.dp,
                    ),
                    border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
                ) {
                    Image(
                        imageVector = ArkIcons.Remove,
                        colorFilter = ColorFilter.tint(Color(0xFFBDBDBD)),
                        contentDescription = null,
                    )
                }
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .offset((-1).dp, 0.dp)
                        .zIndex(1f)
                        .height(36.dp)
                        .width(26.dp),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 8.dp,
                    ),
                    border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
                ) {
                    Image(
                        imageVector = ArkIcons.Add,
                        colorFilter = ColorFilter.tint(Color(0xFFBDBDBD)),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
            OrderPriceBox(
                modifier = Modifier.padding(horizontal = 8.dp),
                prefix = "수량",
                price = "0",
                suffix = "BTC",
            )
            Spacer(modifier = Modifier.padding(20.dp))
            OrderPriceBox(
                modifier = Modifier.padding(horizontal = 8.dp),
                prefix = "총액",
                price = "0",
                suffix = "KRW",
            )
            Text(
                text = "로그인",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .padding(horizontal = 8.dp)
                    .background(
                        color = Color(0xFFE0274F),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .wrapContentHeight(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(
                    text = "수수료율 0.2%",
                    color = Color(0xFFBDBDBD),
                    fontSize = 11.sp,
                )
            }
            Row {
                Text(
                    text = "최소주문금액 5,000 KRW",
                    color = Color(0xFFBDBDBD),
                    fontSize = 11.sp,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "주문 유형",
                    color = Color(0xFFBDBDBD),
                    fontSize = 11.sp,
                )
            }
        }
    }
}

@Composable
fun OrderPriceBox(
    modifier: Modifier = Modifier,
    prefix: String = "가격",
    price: String = "56,912,000",
    suffix: String = "",
) {
    Row(
        modifier = modifier
            .height(36.dp)
            .background(
                Color(0xFFF8F8F9),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = prefix,
            modifier = Modifier.wrapContentWidth(),
            color = Color(0xFFBDBDBD),
        )
        Text(
            text = price,
            modifier = Modifier
                .weight(1f)
                .clickable {
                },
            color = Color(0xFF18191C),
            textAlign = TextAlign.End,
        )
        if (suffix.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(1.dp))
            Text(
                text = suffix,
                modifier = Modifier.wrapContentWidth(),
                color = Color(0xFFBDBDBD),
            )
        }
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
