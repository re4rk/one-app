package com.re4rk.oneapp.domain.repository

import com.re4rk.oneapp.domain.model.KoGpt

interface KoGptRepository {
    suspend fun postChat(authorization: String, prompt: String, maxTokens: Int): Result<KoGpt>
}
