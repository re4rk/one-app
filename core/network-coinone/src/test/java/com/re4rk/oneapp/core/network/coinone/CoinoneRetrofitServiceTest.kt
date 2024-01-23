package com.re4rk.oneapp.core.network.coinone

import com.re4rk.oneapp.core.network.coinone.di.ApiModule
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CoinoneRetrofitServiceTest {
    private var coinoneRetrofitService: CoinoneRetrofitService =
        ApiModule.provideNormalRetrofit(
            ApiModule.provideBaseURL(),
            ApiModule.provideRetrofitConverterFactory(),
        ).create(CoinoneRetrofitService::class.java)

    @Test
    fun `오더북 조회가 가능하다`(): Unit = assertDoesNotThrow {
        runBlocking {
            coinoneRetrofitService.getOrderBook("KRW", "BTC")
        }
    }

    @Test
    fun `티커 조회가 가능하다`(): Unit = assertDoesNotThrow {
        runBlocking {
            coinoneRetrofitService.getTicker("KRW", "BTC")
        }
    }
}
