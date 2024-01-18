package com.re4rk.oneapp.core.networkcoinone.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderBook(
    @SerialName("asks")
    val asks: List<Ask>,

    @SerialName("bids")
    val bids: List<Bid>,

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
)
