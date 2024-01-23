package com.re4rk.oneapp.core.network.coinone.model

import com.re4rk.oneapp.core.model.coinone.Ticker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickerDto(
    @SerialName("best_asks")
    val bestAsks: List<AskDto>,
    @SerialName("best_bids")
    val bestBids: List<BidDto>,
    @SerialName("first")
    val first: String,
    @SerialName("high")
    val high: String,
    @SerialName("id")
    val id: String,
    @SerialName("last")
    val last: String,
    @SerialName("low")
    val low: String,
    @SerialName("quote_currency")
    val quoteCurrency: String,
    @SerialName("quote_volume")
    val quoteVolume: String,
    @SerialName("target_currency")
    val targetCurrency: String,
    @SerialName("target_volume")
    val targetVolume: String,
    @SerialName("timestamp")
    val timestamp: Long,
) {
    fun toDomain() = Ticker(
        bestAsks = bestAsks.map { it.toDomain() },
        bestBids = bestBids.map { it.toDomain() },
        first = first,
        high = high,
        id = id,
        last = last,
        low = low,
        quoteCurrency = quoteCurrency,
        quoteVolume = quoteVolume,
        targetCurrency = targetCurrency,
        targetVolume = targetVolume,
        timestamp = timestamp,
    )
}
