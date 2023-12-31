package com.re4rk.presentation.designSystem.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.re4rk.presentation.designSystem.icon.ArkIcons

@Composable
fun RowScope.ArkNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ArkNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = ArkNavigationDefaults.navigationContentColor(),
            selectedTextColor = ArkNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = ArkNavigationDefaults.navigationContentColor(),
            indicatorColor = ArkNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun ArkNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = ArkNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}

@Composable
fun ArkNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = ArkNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = ArkNavigationDefaults.navigationContentColor(),
            selectedTextColor = ArkNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = ArkNavigationDefaults.navigationContentColor(),
            indicatorColor = ArkNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun ArkNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = ArkNavigationDefaults.navigationContentColor(),
        header = header,
        content = content,
    )
}

object ArkNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}

private val ArkNavigationDestinations = listOf(
    ArkIcons.Search to "Search",
    ArkIcons.Home to "Home",
    ArkIcons.List to "List",
)

@Composable
@Preview
fun ArkNavigationBarPreview() {
    ArkNavigationBar {
        ArkNavigationDestinations.forEach { (icon, label) ->
            ArkNavigationBarItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
            )
        }
    }
}

@Composable
@Preview
fun ArkNavigationRailPreview() {
    ArkNavigationRail {
        ArkNavigationDestinations.forEach { (icon, label) ->
            ArkNavigationRailItem(
                selected = false,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                },
            )
        }
    }
}
