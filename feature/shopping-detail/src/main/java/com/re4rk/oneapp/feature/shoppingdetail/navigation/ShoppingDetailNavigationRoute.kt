package com.re4rk.oneapp.feature.shoppingdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.re4rk.oneapp.feature.shoppingdetail.ShoppingDetailRoute

const val PRODUCT_ID = "productId"
const val shoppingDetailNavigationRoute = "shopping_detail_route/{$PRODUCT_ID}"

fun NavController.navigateToShoppingDetail(
    productId: Long,
    navOptions: NavOptions? = null,
) {
    this.navigate(
        shoppingDetailNavigationRoute.replace("{$PRODUCT_ID}", productId.toString()),
        navOptions,
    )
}

fun NavGraphBuilder.shoppingDetailScreen() {
    composable(
        route = shoppingDetailNavigationRoute,
        arguments = listOf(
            navArgument(PRODUCT_ID) { type = NavType.LongType },
        ),
    ) {
        ShoppingDetailRoute(
            productId = it.arguments?.getLong(PRODUCT_ID) ?: 0,
        )
    }
}
