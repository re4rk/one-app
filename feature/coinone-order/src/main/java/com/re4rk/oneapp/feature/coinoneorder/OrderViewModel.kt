package com.re4rk.oneapp.feature.coinoneorder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.oneapp.core.data.coinone.CoinoneRepository
import com.re4rk.oneapp.core.model.coinone.OrderBook
import com.re4rk.oneapp.core.model.coinone.Ticker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val coinoneRepository: CoinoneRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<OrderScreenUiState> =
        MutableStateFlow(OrderScreenUiState())
    val uiState: StateFlow<OrderScreenUiState> = _uiState

    val orderBook: StateFlow<Result<OrderBook>> = flow {
        while (true) {
            emit(coinoneRepository.getOrderBook("KRW", "BTC"))
            delay(1000)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Result.failure(Throwable("initial value")),
    )

    val ticker: StateFlow<Result<Ticker>> = flow {
        // repeat emit
        while (true) {
            emit(coinoneRepository.getTicker("KRW", "BTC"))
            delay(1000)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Result.failure(Throwable("initial value")),
    )
}
