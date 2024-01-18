package com.re4rk.oneapp.feature.coinoneorder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.re4rk.oneapp.feature.coinoneorder.OrderRoute

const val orderNavigationRoute = "coinone_order_route"

fun NavController.navigateToCoinoneOrder(navOptions: NavOptions? = null) {
    this.navigate(orderNavigationRoute, navOptions)
}

fun NavGraphBuilder.coinoneOrderScreen() {
    composable(route = orderNavigationRoute) {
        OrderRoute()
    }
}
