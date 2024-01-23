package com.re4rk.oneapp.core.data.coinone

import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker

interface CoinoneRepository {
    suspend fun getOrderBook(quoteCurrency: String, targetCurrency: String): Result<OrderBook>
    suspend fun getTicker(quoteCurrency: String, targetCurrency: String): Result<Ticker>
}
