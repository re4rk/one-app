package com.re4rk.oneapp.data.service

import com.re4rk.oneapp.data.di.KakaoModule
import com.re4rk.oneapp.data.model.koGpt.KoGptRequest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class KoGptServiceTest {

    val service = KakaoModule.provideKoGptService(
        KakaoModule.provideKakaoRetrofit(
            KakaoModule.provideKoGptUrl(),
            KakaoModule.provideOkHttpClient(),
        ),
    )

    @Test
    fun testPostChat() {
        runBlocking {
            val result = service.postChat(
                authorization = "KakaoAK " + "KakaoAK 0b9e4b9b4b9b9b9b9b9b9b9b9b9b9b9b",
                koGptRequest = KoGptRequest(
                    prompt = "안녕!",
                    maxTokens = 1024,
                ),
            )
        }
    }
}
