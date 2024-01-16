package com.re4rk.oneapp.feature.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.oneapp.domain.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ShoppingViewModel : ViewModel() {
    val products: StateFlow<List<Product>> = flowOf<List<Product>>().onStart {
        repeat(10) {
            Product(
                id = it.toLong(),
                title = "Title",
                price = "Price",
                description = "Description",
                imageUrl = "https://picsum.photos/600/${600 + it}",
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )
}
