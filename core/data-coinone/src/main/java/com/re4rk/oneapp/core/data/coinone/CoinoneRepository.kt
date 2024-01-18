package com.re4rk.oneapp.core.data.coinone

import com.re4rk.oneapp.core.model.coinone.OrderBook

interface CoinoneRepository {
    suspend fun getOrderBook(quoteCurrency: String, targetCurrency: String): Result<OrderBook>
}
