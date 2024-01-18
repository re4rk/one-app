package com.re4rk.oneapp.core.network.coinone.model

import com.re4rk.oneapp.core.model.coinone.OrderBook
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderBookDto(
    @SerialName("asks")
    val asks: List<AskDto>,

    @SerialName("bids")
    val bids: List<BidDto>,

    @SerialName("error_code")
    val errorCode: String,

    @SerialName("id")
    val id: String,

    @SerialName("order_book_unit")
    val orderBookUnit: String,

    @SerialName("quote_currency")
    val quoteCurrency: String,

    @SerialName("result")
    val result: String,

    @SerialName("target_currency")
    val targetCurrency: String,

    @SerialName("timestamp")
    val timestamp: Long,
) {
    fun toDomain() = OrderBook(
        asks = asks.map { it.toDomain() },
        bids = bids.map { it.toDomain() },
        errorCode = errorCode,
        id = id,
        orderBookUnit = orderBookUnit,
        quoteCurrency = quoteCurrency,
        result = result,
        targetCurrency = targetCurrency,
        timestamp = timestamp,
    )
}
