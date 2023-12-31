package com.re4rk.app

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.re4rk.navigation.TopLevelDestination
import com.re4rk.presentation.designSystem.component.ArkNavigationBar
import com.re4rk.presentation.designSystem.component.ArkNavigationBarItem
import com.re4rk.presentation.ui.home.navigation.homeNavigationRoute
import com.re4rk.presentation.ui.home.navigation.homeScreen
import com.re4rk.presentation.ui.home.navigation.navigateToHome
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Scaffold(
                bottomBar = { MainScreenBottomBar(navController) },
            ) {
                NavHost(
                    navController = navController,
                    startDestination = homeNavigationRoute,
                ) {
                    homeScreen()
                }
            }
        }
    }

    @Composable
    private fun MainScreenBottomBar(
        navController: NavController,
    ) = ArkNavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        TopLevelDestination.values().forEach { destination ->
            ArkNavigationBarItem(
                selected = true,
                onClick = {
                    when (destination) {
                        TopLevelDestination.HOME -> {
                            navController.navigateToHome()
                        }
                    }
                },
                icon = {
                    Image(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = destination.name)
                },
            )
        }
    }
}
