package com.re4rk.oneapp.core.network.coinone.model

import com.re4rk.oneapp.core.model.coinone.Ticker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TickersDto(
    @SerialName("error_code")
    val errorCode: String,
    @SerialName("result")
    val result: String,
    @SerialName("server_time")
    val serverTime: Long,
    @SerialName("tickers")
    val tickers: List<TickerDto>,
) {
    fun toDomain(): List<Ticker> = tickers.map { it.toDomain() }
    fun toSingleDomain(): Ticker = tickers.first().toDomain()
}
