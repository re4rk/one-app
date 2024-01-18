package com.re4rk.oneapp.core.networkcoinone.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ask(
    @SerialName("price")
    val price: String,

    @SerialName("qty")
    val qty: String,
)
