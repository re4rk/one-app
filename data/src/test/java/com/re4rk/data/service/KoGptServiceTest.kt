package com.re4rk.data.service

import com.re4rk.data.di.KakaoModule
import com.re4rk.data.model.koGpt.KoGptRequest
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KoGptServiceTest {

    val service = KakaoModule.provideKoGptService(
        KakaoModule.provideKakaoRetrofit(
            KakaoModule.provideKoGptUrl(),
            KakaoModule.provideOkHttpClient(),
        ),
    )

    @Test
    fun aasdasd() {
        assertThat(1).isEqualTo(1)
    }

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
