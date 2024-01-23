package com.re4rk.oneapp.core.model.coinone

data class Ticker(
    val bestAsks: List<Ask>,
    val bestBids: List<Bid>,
    val first: String,
    val high: String,
    val id: String,
    val last: String,
    val low: String,
    val quoteCurrency: String,
    val quoteVolume: String,
    val targetCurrency: String,
    val targetVolume: String,
    val timestamp: Long,
)
