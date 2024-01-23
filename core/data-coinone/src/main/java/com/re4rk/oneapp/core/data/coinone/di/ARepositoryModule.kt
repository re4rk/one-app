package com.re4rk.oneapp.core.data.coinone.di

import com.re4rk.oneapp.core.data.coinone.CoinoneRepository
import com.re4rk.oneapp.core.data.coinone.DefaultCoinoneRepository
import com.re4rk.oneapp.core.network.coinone.CoinoneRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ARepositoryModule {
    @Provides
    fun provideCoinoneRepository(
        coinoneRetrofitService: CoinoneRetrofitService,
    ): CoinoneRepository {
        return DefaultCoinoneRepository(
            coinoneRetrofitService = coinoneRetrofitService,
        )
    }
}
