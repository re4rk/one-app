package com.re4rk.presentation.ui.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.re4rk.presentation.ui.menu.MenuRoute

const val LINKED_NEWS_RESOURCE_ID = "linkedNewsResourceId"
const val MenuNavigationRoute = "menu_route/{$LINKED_NEWS_RESOURCE_ID}"
private const val DEEP_LINK_URI_PATTERN =
    "https://www.re4rk.com/Menu/{$LINKED_NEWS_RESOURCE_ID}"

fun NavController.navigateToMenu(navOptions: NavOptions? = null) {
    this.navigate(MenuNavigationRoute, navOptions)
}

fun NavGraphBuilder.menuScreen() {
    composable(
        route = MenuNavigationRoute,
        deepLinks = listOf(
            navDeepLink { uriPattern = DEEP_LINK_URI_PATTERN },
        ),
        arguments = listOf(
            navArgument(LINKED_NEWS_RESOURCE_ID) { type = NavType.StringType },
        ),
    ) {
        MenuRoute()
    }
}
