package com.re4rk.oneapp.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.re4rk.oneapp.app.navigation.TopLevelDestination

@Composable
fun rememberOneAppState(
    navController: NavHostController = rememberNavController(),
): OneAppState {
    return OneAppState(navController)
}

@Stable
class OneAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()
}
