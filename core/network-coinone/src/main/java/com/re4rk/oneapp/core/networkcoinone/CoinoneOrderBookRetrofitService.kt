package com.re4rk.oneapp.core.networkcoinone

import com.re4rk.oneapp.core.networkcoinone.model.OrderBook
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinoneOrderBookRetrofitService {
    @GET("/public/v2/orderbook/{quoteCurrency}/{targetCurrency}")
    suspend fun getOrderBook(
        @Path("quoteCurrency") quoteCurrency: String,
        @Path("targetCurrency") targetCurrency: String,
    ): Response<OrderBook>
}
