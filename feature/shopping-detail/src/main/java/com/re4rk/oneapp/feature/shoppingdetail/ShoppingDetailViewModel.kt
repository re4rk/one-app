package com.re4rk.oneapp.feature.shoppingdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.oneapp.domain.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingDetailViewModel @Inject constructor(
    private val shoppingDetailRepository: ShoppingRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ShoppingDetailUiState> =
        MutableStateFlow(ShoppingDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun syncProduct(productId: Long) {
        viewModelScope.launch {
            shoppingDetailRepository.products.collect { products ->
                _uiState.value = when (val product = products.firstOrNull { it.id == productId }) {
                    null -> ShoppingDetailUiState.Error("Product not found")
                    else -> ShoppingDetailUiState.Success(product)
                }
            }
        }
    }
}
