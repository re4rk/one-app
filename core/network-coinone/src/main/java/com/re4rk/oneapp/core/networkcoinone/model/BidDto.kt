package com.re4rk.oneapp.core.networkcoinone.model

import com.re4rk.oneapp.core.modelcoinone.coinone.model.Bid
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
