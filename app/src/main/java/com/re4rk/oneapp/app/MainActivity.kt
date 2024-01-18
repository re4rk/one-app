package com.re4rk.oneapp.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.re4rk.app.R
import com.re4rk.oneapp.app.navigation.TopLevelDestination
import com.re4rk.oneapp.core.designsystem.component.ArkNavigationBar
import com.re4rk.oneapp.core.designsystem.component.ArkNavigationBarItem
import com.re4rk.oneapp.core.designsystem.component.ArkTopAppBar
import com.re4rk.oneapp.feature.coinoneorder.navigation.coinoneOrderScreen
import com.re4rk.oneapp.feature.coinoneorder.navigation.navigateToCoinoneOrder
import com.re4rk.oneapp.feature.shopping.navigation.navigateToShopping
import com.re4rk.oneapp.feature.shopping.navigation.shoppingScreen
import com.re4rk.oneapp.feature.shoppingdetail.navigation.navigateToShoppingDetail
import com.re4rk.oneapp.feature.shoppingdetail.navigation.shoppingDetailScreen
import com.re4rk.oneapp.presentation.ui.home.navigation.homeNavigationRoute
import com.re4rk.oneapp.presentation.ui.home.navigation.homeScreen
import com.re4rk.oneapp.presentation.ui.home.navigation.navigateToHome
import com.re4rk.oneapp.presentation.ui.memo.navigation.memoScreen
import com.re4rk.oneapp.presentation.ui.memo.navigation.navigateToMemo
import com.re4rk.oneapp.presentation.ui.menu.navigation.menuScreen
import com.re4rk.oneapp.presentation.ui.menu.navigation.navigateToMenu
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val oneAppState = rememberOneAppState()

            Scaffold(
                topBar = {
                    ArkTopAppBar(
                        titleRes = R.string.app_name,
                        navigationIcon = Icons.Default.ArrowBack,
                        navigationIconContentDescription = null,
                        actionIcon = Icons.Default.Menu,
                        actionIconContentDescription = null,
                        onNavigationClick = { /* TODO */ },
                        onActionClick = { /* TODO */ },
                    )
                },
                bottomBar = { MainScreenBottomBar(oneAppState) },
            ) { innerPadding ->
                NavHost(
                    navController = oneAppState.navController,
                    startDestination = homeNavigationRoute,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    memoScreen()
                    shoppingDetailScreen()
                    coinoneOrderScreen()
                    shoppingScreen { productId ->
                        oneAppState.navController.navigateToShoppingDetail(productId)
                    }
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
                        TopLevelDestination.ORDER -> oneAppState.navController.navigateToCoinoneOrder()
                        TopLevelDestination.SHOPPING -> oneAppState.navController.navigateToShopping()
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
