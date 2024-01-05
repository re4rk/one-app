package com.re4rk.domain.model

data class KoGpt(
    val text: String,
    val tokens: Int,
    val promptTokens: Int,
    val generatedTokens: Int,
    val totalTokens: Int,
    val generations: List<KoGptGeneration>,
)
