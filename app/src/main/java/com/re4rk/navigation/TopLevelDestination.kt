/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.re4rk.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.re4rk.oneapp.core.designsystem.icon.ArkIcons
import com.re4rk.presentation.R as presentationR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    MEMO(
        selectedIcon = ArkIcons.Memo,
        unselectedIcon = ArkIcons.Memo,
        iconTextId = presentationR.string.memo,
        titleTextId = presentationR.string.memo,
    ),
    HOME(
        selectedIcon = ArkIcons.Home,
        unselectedIcon = ArkIcons.Home,
        iconTextId = presentationR.string.home,
        titleTextId = presentationR.string.home,
    ),
    MENU(
        selectedIcon = ArkIcons.Menu,
        unselectedIcon = ArkIcons.Menu,
        iconTextId = presentationR.string.menu,
        titleTextId = presentationR.string.menu,
    ),
}
