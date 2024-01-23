package com.re4rk.oneapp.core.network.coinone

import com.re4rk.oneapp.core.network.coinone.di.ApiModule
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
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

    @Test
    fun `추가 데이터를 포함한 티커 조회가 가능하다`(): Unit = assertDoesNotThrow {
        val tickers = runBlocking {
            coinoneRetrofitService
                .getTicker("KRW", "BTC", true)
                .body()!!
                .toDomain()
        }

        assertAll(
            { assertThat(tickers.size).isEqualTo(1) },
            { assertThat(tickers[0].yesterdayFirst).isNotNull() },
            { assertThat(tickers[0].yesterdayLast).isNotNull() },
            { assertThat(tickers[0].yesterdayHigh).isNotNull() },
            { assertThat(tickers[0].yesterdayLow).isNotNull() },
            { assertThat(tickers[0].yesterdayQuoteVolume).isNotNull() },
            { assertThat(tickers[0].yesterdayTargetVolume).isNotNull() },
        )
    }
}
