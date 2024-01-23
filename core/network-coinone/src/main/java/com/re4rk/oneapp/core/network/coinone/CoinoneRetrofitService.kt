package com.re4rk.oneapp.core.network.coinone

import com.re4rk.oneapp.core.network.coinone.model.OrderBookDto
import com.re4rk.oneapp.core.network.coinone.model.TickersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinoneRetrofitService {
    @GET("/public/v2/orderbook/{quoteCurrency}/{targetCurrency}")
    suspend fun getOrderBook(
        @Path("quoteCurrency") quoteCurrency: String,
        @Path("targetCurrency") targetCurrency: String,
    ): Response<OrderBookDto>

    @GET("/public/v2/ticker_new/{quote_currency}/{target_currency}")
    suspend fun getTicker(
        @Path("quote_currency") quoteCurrency: String,
        @Path("target_currency") targetCurrency: String,
    ): Response<TickersDto>
}
