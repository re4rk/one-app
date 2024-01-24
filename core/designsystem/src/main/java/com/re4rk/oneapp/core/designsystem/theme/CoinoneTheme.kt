package com.re4rk.oneapp.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

object CoinoneTheme {
    /**
     * Retrieves the current [ColorScheme] at the call site's position in the hierarchy.
     */
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme.copy(
            primary = Color(0xFF1763B6),
            primaryContainer = Color(0xFFF6FAFF),
        )

    /**
     * Retrieves the current [Typography] at the call site's position in the hierarchy.
     */
    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography.copy(
            displayLarge = MaterialTheme.typography.displayLarge.copy(
                color = Color(0xFF1763B6),
                fontSize = 48.sp,
            ),
            displayMedium = MaterialTheme.typography.displayMedium.copy(
                color = Color(0xFF1763B6),
                fontSize = 36.sp,
            ),
            displaySmall = MaterialTheme.typography.displaySmall.copy(
                color = Color(0xFF1763B6),
                fontSize = 24.sp,
            ),
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                color = Color(0xFF1763B6),
                fontSize = 24.sp,
            ),
            titleMedium = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFF1763B6),
                fontSize = 20.sp,
            ),
            titleSmall = MaterialTheme.typography.titleSmall.copy(
                color = Color(0xFF1763B6),
                fontSize = 16.sp,
            ),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                color = Color(0xFF1763B6),
                fontSize = 14.sp,
            ),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF1763B6),
                fontSize = 12.sp,
            ),
            bodySmall = MaterialTheme.typography.bodySmall.copy(
                color = Color(0xFF1763B6),
                fontSize = 10.sp,
            ),
            labelLarge = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF1763B6),
                fontSize = 11.sp,
            ),
            labelMedium = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFF1763B6),
                fontSize = 10.sp,
            ),
            labelSmall = MaterialTheme.typography.labelSmall.copy(
                color = Color(0xFF1763B6),
                fontSize = 8.sp,
            ),
        )

    /**
     * Retrieves the current [Shapes] at the call site's position in the hierarchy.
     */
    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes
}
