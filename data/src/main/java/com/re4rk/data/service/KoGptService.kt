package com.re4rk.data.service

import com.re4rk.data.model.koGpt.KoGptResponse
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface KoGptService {
    @POST("/v1/inference/kogpt/generation")
    fun postChat(
        @Header("Authorization") authorization: String,
        @Query("prompt") prompt: String,
        @Query("max_tokens") maxTokens: Int,
    ): KoGptResponse
}
