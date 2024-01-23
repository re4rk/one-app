package com.re4rk.oneapp.feature.coinoneorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.oneapp.core.data.coinone.CoinoneRepository
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val coinoneRepository: CoinoneRepository,
) : ViewModel() {
    val orderBook: StateFlow<Result<OrderBook>> = flow {
        emit(coinoneRepository.getOrderBook("KRW", "BTC"))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Result.failure(Throwable("initial value")),
    )

    val ticker: StateFlow<Result<Ticker>> = flow {
        emit(coinoneRepository.getTicker("KRW", "BTC"))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Result.failure(Throwable("initial value")),
    )
}
