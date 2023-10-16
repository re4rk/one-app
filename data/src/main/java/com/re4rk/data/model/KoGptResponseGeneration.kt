package com.re4rk.data.model

import com.re4rk.domain.model.KoGptGeneration
import kotlinx.serialization.SerialName

data class KoGptResponseGeneration(
    @SerialName("text") val text: String,
    @SerialName("tokens") val tokens: Int,
) {
    fun toDomain() = KoGptGeneration(
        text = text,
        tokens = tokens,
    )
}
