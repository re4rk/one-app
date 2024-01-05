package com.re4rk.data.service

import com.re4rk.data.model.koGpt.KoGptRequest
import com.re4rk.data.model.koGpt.KoGptResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface KoGptService {
    @POST("/v1/inference/kogpt/generation")
    suspend fun postChat(
        @Header("Authorization") authorization: String,
        @Body koGptRequest: KoGptRequest,
    ): Response<KoGptResponse>
}
