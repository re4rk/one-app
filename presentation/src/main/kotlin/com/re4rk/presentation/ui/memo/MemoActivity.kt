package com.re4rk.presentation.ui.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemoActivity : ComponentActivity() {
    private val vm: MemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoScreen(
                { finish() },
                { /* TODO */ },
                { vm.addMemo(it) },
                vm,
            )
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MemoActivity::class.java)
        }
    }
}
