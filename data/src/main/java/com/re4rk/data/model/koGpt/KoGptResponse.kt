package com.re4rk.data.model.koGpt

import com.re4rk.domain.model.KoGpt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KoGptResponse(
    @SerialName("generations") val generations: List<KoGptResponseGeneration>,
    @SerialName("id") val id: String,
    @SerialName("usage") val usage: KoGptResponseUsage,
) {
    fun toDomain() = KoGpt(
        text = generations.first().text,
        tokens = generations.first().tokens,
        promptTokens = usage.promptTokens,
        generatedTokens = usage.generatedTokens,
        totalTokens = usage.totalTokens,
        generations = generations.map { it.toDomain() },
    )
}
