package com.re4rk.oneapp.domain.repository

import com.re4rk.oneapp.domain.model.CartProduct
import com.re4rk.oneapp.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ShoppingRepository {
    val products: Flow<List<Product>>
    val cartProducts: Flow<List<CartProduct>>

    fun updateCartProductCount(productId: Long, count: Int)
}
