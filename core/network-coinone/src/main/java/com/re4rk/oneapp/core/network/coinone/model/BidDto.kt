package com.re4rk.oneapp.core.network.coinone.model

import com.re4rk.oneapp.core.model.coinone.Bid
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BidDto(
    @SerialName("price")
    val price: String,

    @SerialName("qty")
    val qty: String,
) {
    fun toDomain() = Bid(
        price = price,
        qty = qty,
    )
}
