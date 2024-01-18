package com.re4rk.oneapp.core.data.coinone

import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.network.coinone.CoinoneOrderBookRetrofitService

class DefaultCoinoneRepository(
    private val coinoneOrderBookRetrofitService: CoinoneOrderBookRetrofitService,
) : CoinoneRepository {
    override suspend fun getOrderBook(
        quoteCurrency: String,
        targetCurrency: String,
    ): Result<OrderBook> {
        val response = coinoneOrderBookRetrofitService.getOrderBook(
            quoteCurrency = quoteCurrency,
            targetCurrency = targetCurrency,
        )

        return if (response.isSuccessful) {
            val body = response.body()!!
            Result.success(body.toDomain())
        } else {
            Result.failure(Throwable(response.message()))
        }
    }
}
