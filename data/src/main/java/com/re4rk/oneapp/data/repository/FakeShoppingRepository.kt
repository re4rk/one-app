package com.re4rk.oneapp.data.repository

import com.re4rk.oneapp.domain.model.CartProduct
import com.re4rk.oneapp.domain.model.Product
import com.re4rk.oneapp.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

class FakeShoppingRepository : ShoppingRepository {
    override fun changeCartProductCount(productId: Long, count: Int) {
        _cartProductCounts[productId] = count
    }

    private val _products = (0..10).map {
        Product(
            id = it.toLong(),
            title = "Title",
            price = "Price",
            description = "Description",
            imageUrl = "https://picsum.photos/600/${600 + it}",
        )
    }

    private val _cartProductCounts: MutableMap<Long, Int> = (0..10)
        .map { it.toLong() to it }
        .toMap()
        .toMutableMap()

    override val products: Flow<List<Product>> = flowOf<List<Product>>()
        .onStart { emit(_products) }

    override val cartProducts: Flow<List<CartProduct>> = flowOf<List<CartProduct>>()
        .onStart {
            emit(
                _products.indices.map {
                    CartProduct(
                        product = _products[it],
                        count = _cartProductCounts[it.toLong()] ?: 0,
                    )
                },
            )
        }
}
