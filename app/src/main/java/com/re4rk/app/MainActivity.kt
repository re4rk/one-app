package com.re4rk.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.re4rk.navigation.TopLevelDestination
import com.re4rk.oneapp.core.designsystem.component.ArkNavigationBar
import com.re4rk.oneapp.core.designsystem.component.ArkNavigationBarItem
import com.re4rk.presentation.ui.home.navigation.homeNavigationRoute
import com.re4rk.presentation.ui.home.navigation.homeScreen
import com.re4rk.presentation.ui.home.navigation.navigateToHome
import com.re4rk.presentation.ui.memo.navigation.memoScreen
import com.re4rk.presentation.ui.memo.navigation.navigateToMemo
import com.re4rk.presentation.ui.menu.navigation.menuScreen
import com.re4rk.presentation.ui.menu.navigation.navigateToMenu
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val oneAppState = rememberOneAppState()

            Scaffold(
                bottomBar = { MainScreenBottomBar(oneAppState) },
            ) {
                NavHost(
                    navController = oneAppState.navController,
                    startDestination = homeNavigationRoute,
                ) {
                    memoScreen()
                    homeScreen()
                    menuScreen()
                }
            }
        }
    }

    @Composable
    private fun MainScreenBottomBar(
        oneAppState: OneAppState,
    ) = ArkNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        oneAppState.topLevelDestinations.forEach { destination ->
            val selected =
                oneAppState.currentDestination.isTopLevelDestinationInHierarchy(destination)

            ArkNavigationBarItem(
                selected = selected,
                onClick = {
                    when (destination) {
                        TopLevelDestination.HOME -> oneAppState.navController.navigateToHome()
                        TopLevelDestination.MENU -> oneAppState.navController.navigateToMenu()
                        TopLevelDestination.MEMO -> oneAppState.navController.navigateToMemo()
                    }
                },
                icon = {
                    Image(
                        imageVector = destination.unselectedIcon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = destination.name)
                },
            )
        }
    }

    private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
        this?.hierarchy?.any {
            Log.d("MainActivity", "isTopLevelDestinationInHierarchy: ${it.route}")
            it.route?.contains(destination.name, true) ?: false
        } ?: false
}
