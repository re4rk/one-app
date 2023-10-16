package com.re4rk.domain.repository

import com.re4rk.domain.model.KoGpt

interface KoGptRepository {
    fun postChat(authorization: String, prompt: String, maxTokens: Int): Result<KoGpt>
}
