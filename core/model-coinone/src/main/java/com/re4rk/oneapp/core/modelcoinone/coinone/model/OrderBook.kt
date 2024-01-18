package com.re4rk.oneapp.core.modelcoinone.coinone.model

data class OrderBook(
    val asks: List<Ask>,
    val bids: List<Bid>,
    val errorCode: String,
    val id: String,
    val orderBookUnit: String,
    val quoteCurrency: String,
    val result: String,
    val targetCurrency: String,
    val timestamp: Long,
)
