package com.re4rk.oneapp.core.networkcoinone.model

import com.re4rk.oneapp.core.modelcoinone.coinone.model.Ask
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
