package com.re4rk.oneapp.core.data.coinone

import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker
import com.re4rk.oneapp.core.network.coinone.CoinoneRetrofitService

class DefaultCoinoneRepository(
    private val coinoneRetrofitService: CoinoneRetrofitService,
) : CoinoneRepository {
    override suspend fun getOrderBook(
        quoteCurrency: String,
        targetCurrency: String,
    ): Result<OrderBook> {
        val response = coinoneRetrofitService.getOrderBook(
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

    override suspend fun getTicker(
        quoteCurrency: String,
        targetCurrency: String,
    ): Result<List<Ticker>> {
        val response = coinoneRetrofitService.getTicker(
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
