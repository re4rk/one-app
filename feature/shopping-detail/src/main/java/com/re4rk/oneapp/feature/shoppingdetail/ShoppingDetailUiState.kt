package com.re4rk.oneapp.feature.shoppingdetail

import androidx.compose.runtime.Stable
import com.re4rk.oneapp.domain.model.Product

@Stable
sealed interface ShoppingDetailUiState {
    object Loading : ShoppingDetailUiState
    data class Success(val product: Product) : ShoppingDetailUiState
    data class Error(val message: String) : ShoppingDetailUiState
}
