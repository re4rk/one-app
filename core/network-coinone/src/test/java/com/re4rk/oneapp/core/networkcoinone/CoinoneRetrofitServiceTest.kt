package com.re4rk.oneapp.core.networkcoinone

import com.re4rk.oneapp.core.networkcoinone.di.ApiModule
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CoinoneRetrofitServiceTest {
    private var coinoneOrderBookRetrofitService: CoinoneOrderBookRetrofitService =
        ApiModule.providesNormalRetrofit(
            ApiModule.provideBaseURL(),
            ApiModule.provideRetrofitConverterFactory(),
        ).create(CoinoneOrderBookRetrofitService::class.java)

    @Test
    fun `오더북 조회가 가능하다`(): Unit = assertDoesNotThrow {
        runBlocking {
            coinoneOrderBookRetrofitService.getOrderBook("KRW", "BTC")
        }
    }
}
