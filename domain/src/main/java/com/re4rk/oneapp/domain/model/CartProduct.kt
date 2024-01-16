package com.re4rk.oneapp.domain.model

data class CartProduct(
    private val product: Product,
    val count: Int,
) {
    val title: String
        get() = product.title
    val description: String
        get() = product.description
    val price: String
        get() = product.price
    val imageUrl: String
        get() = product.imageUrl
    val productId: Long
        get() = product.id
}
