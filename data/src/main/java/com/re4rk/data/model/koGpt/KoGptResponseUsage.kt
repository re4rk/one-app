package com.re4rk.data.model.koGpt

import kotlinx.serialization.SerialName

data class KoGptResponseUsage(
    @SerialName("generated_tokens") val generatedTokens: Int,
    @SerialName("prompt_tokens") val promptTokens: Int,
    @SerialName("total_tokens") val totalTokens: Int,
)
