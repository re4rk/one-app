package com.re4rk.data.model.koGpt

import com.re4rk.domain.model.KoGptGeneration
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KoGptResponseGeneration(
    @SerialName("text") val text: String,
    @SerialName("tokens") val tokens: Int,
) {
    fun toDomain() = KoGptGeneration(
        text = text,
        tokens = tokens,
    )
}
