package com.re4rk.presentation.ui.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.re4rk.presentation.ui.menu.MenuRoute

const val menuNavigationRoute = "menu_route"

fun NavController.navigateToMenu(navOptions: NavOptions? = null) {
    this.navigate(menuNavigationRoute, navOptions)
}

fun NavGraphBuilder.menuScreen() {
    composable(route = menuNavigationRoute) {
        MenuRoute()
    }
}
