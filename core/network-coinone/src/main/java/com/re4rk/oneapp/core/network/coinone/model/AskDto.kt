package com.re4rk.oneapp.core.network.coinone.model

import com.re4rk.oneapp.core.model.coinone.Ask
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AskDto(
    @SerialName("price")
    val price: String,

    @SerialName("qty")
    val qty: String,
) {
    fun toDomain() = Ask(
        price = price,
        qty = qty,
    )
}
