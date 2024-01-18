package com.re4rk.oneapp.core.data.coinone.di

import com.re4rk.oneapp.core.data.coinone.CoinoneRepository
import com.re4rk.oneapp.core.data.coinone.DefaultCoinoneRepository
import com.re4rk.oneapp.core.network.coinone.CoinoneOrderBookRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideCoinoneRepository(
        coinoneOrderBookRetrofitService: CoinoneOrderBookRetrofitService,
    ): CoinoneRepository {
        return DefaultCoinoneRepository(
            coinoneOrderBookRetrofitService = coinoneOrderBookRetrofitService,
        )
    }
}
