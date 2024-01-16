package com.re4rk.oneapp.feature.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.oneapp.domain.model.CartProduct
import com.re4rk.oneapp.domain.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository,
) : ViewModel() {
    val products: StateFlow<List<CartProduct>> = shoppingRepository.cartProducts.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

    fun updateCount(id: Long, count: Int) {
        shoppingRepository.updateCartProductCount(id, count)
    }
}
