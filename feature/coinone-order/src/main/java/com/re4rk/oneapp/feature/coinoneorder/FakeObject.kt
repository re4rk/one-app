package com.re4rk.oneapp.feature.coinoneorder

import com.re4rk.oneapp.core.model.coinone.Ask
import com.re4rk.oneapp.core.model.coinone.Bid
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker

internal object FakeObject {
    val orderBook: OrderBook = OrderBook(
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
    )

    val ticker: Ticker = Ticker(
        quoteCurrency = "krw",
        targetCurrency = "btc",
        timestamp = 1706021048381,
        yesterdayFirst = "57868000.0",
        yesterdayHigh = "57868000.0",
        yesterdayLast = "56141000.0",
        yesterdayLow = "55909000.0",
        yesterdayQuoteVolume = "15167970210.1751",
        yesterdayTargetVolume = "267.72109957",
        bestAsks = listOf(Ask("53429000.0", "0.08098676")),
        bestBids = listOf(Bid("53411000.0", "0.1961")),
        first = "56127000.0",
        high = "56350000.0",
        last = "53429000.0",
        low = "53154000.0",
        quoteVolume = "31618861910.0118",
        targetVolume = "578.47273582",
        id = "1706021048381001",
    )
}
