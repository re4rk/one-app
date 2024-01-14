package com.re4rk.oneapp.feature.shopping.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.re4rk.oneapp.feature.shopping.ShoppingScreen

const val shoppingNavigationRoute = "shopping_route"

fun NavController.navigateToShopping(navOptions: NavOptions? = null) {
    this.navigate(shoppingNavigationRoute, navOptions)
}

fun NavGraphBuilder.shoppingScreen() {
    composable(route = shoppingNavigationRoute) {
        ShoppingScreen()
    }
}
