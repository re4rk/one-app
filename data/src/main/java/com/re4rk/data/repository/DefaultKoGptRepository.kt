package com.re4rk.data.repository

import com.re4rk.data.model.koGpt.KoGptRequest
import com.re4rk.data.service.KoGptService
import com.re4rk.domain.model.KoGpt
import com.re4rk.domain.repository.KoGptRepository

class DefaultKoGptRepository(
    private val koGPTService: KoGptService,
) : KoGptRepository {

    override suspend fun postChat(
        authorization: String,
        prompt: String,
        maxTokens: Int,
    ): Result<KoGpt> = Result.success(
        koGPTService.postChat(
            authorization = authorization,
            KoGptRequest(
                prompt = prompt,
                maxTokens = maxTokens,
            ),
        ).body()?.toDomain()!!,
    )
}
