package com.re4rk.oneapp.core.networkcoinone.di

import com.re4rk.oneapp.core.networkcoinone.CoinoneRetrofitService
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlQualifier

class ApiModule {
    @Provides
    fun provideBaseURL(): String = "https://api.coinone.co.kr"

    fun providesNormalRetrofit(
        @BaseUrlQualifier baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .build()

    @Provides
    fun provideTokenRetrofitService(
        retrofit: Retrofit,
    ): CoinoneRetrofitService {
        return retrofit.create(CoinoneRetrofitService::class.java)
    }
}
