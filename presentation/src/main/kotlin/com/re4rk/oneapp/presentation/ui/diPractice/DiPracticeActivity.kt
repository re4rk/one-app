package com.re4rk.presentation.ui.diPractice

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiPracticeActivity : ComponentActivity() {
    private val viewModel: DiPracticeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiPracticeRoot(viewModel)
        }
    }

    companion object {
        fun getIntent(context: Context) = DiPracticeActivity::class.java.let {
            android.content.Intent(context, it)
        }
    }
}
