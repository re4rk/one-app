package com.re4rk.oneapp.core.networkcoinone

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinoneRetrofitService {
    // https://api.coinone.co.kr/public/v2/markets/KRW/BTC
    @GET("/public/v2/markets/{quoteCurrency}/{targetCurrency}")
    suspend fun getOauthToken(
        @Query("quoteCurrency") quoteCurrency: String,
        @Query("targetCurrency") targetCurrency: String,
    ): Response<String>
}
