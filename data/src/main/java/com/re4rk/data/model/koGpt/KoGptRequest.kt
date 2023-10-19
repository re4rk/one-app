package com.re4rk.data.model.koGpt

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KoGptRequest(
    @SerialName("prompt") val prompt: String,
    @SerialName("max_tokens") val maxTokens: Int,
)
