package com.re4rk.presentation.ui.memo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.re4rk.presentation.ui.memo.MemoRoute

const val memoNavigationRoute = "meno_route"

fun NavController.navigateToMemo(navOptions: NavOptions? = null) {
    this.navigate(memoNavigationRoute, navOptions)
}

fun NavGraphBuilder.memoScreen() {
    composable(route = memoNavigationRoute) {
        MemoRoute()
    }
}
