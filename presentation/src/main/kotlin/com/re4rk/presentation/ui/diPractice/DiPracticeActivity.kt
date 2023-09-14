package com.re4rk.presentation.ui.diPractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class DiPracticeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiPracticeRoot()
        }
    }
}
