package com.re4rk.oneapp.data.repository

import com.re4rk.oneapp.domain.model.CartProduct
import com.re4rk.oneapp.domain.model.Product
import com.re4rk.oneapp.domain.repository.ShoppingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart

class FakeShoppingRepository : ShoppingRepository {
    private val _products = (0..10).map {
        Product(
            id = it.toLong(),
            title = "Title",
            price = "Price",
            description = "Description",
            imageUrl = "https://picsum.photos/600/${600 + it}",
        )
    }

    private val _cartProductCounts = (0..10).associateBy { it.toLong() }.toMutableMap()

    private val _cartProducts: MutableSharedFlow<Map<Long, Int>> =
        MutableSharedFlow<Map<Long, Int>>(replay = 1).apply {
            tryEmit(_cartProductCounts)
        }

    override val products: Flow<List<Product>> = flowOf<List<Product>>()
        .onStart { emit(_products) }

    override val cartProducts: Flow<List<CartProduct>> = flow {
        _cartProducts.collect {
            emit(
                _products.map { product ->
                    CartProduct(
                        product = product,
                        count = _cartProductCounts[product.id] ?: 0,
                    )
                },
            )
        }
    }

    override fun updateCartProductCount(productId: Long, count: Int) {
        _cartProducts.tryEmit(
            _cartProductCounts.apply { put(productId, count) },
        )
    }
}
