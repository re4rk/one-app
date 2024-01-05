package com.re4rk.presentation.ui.lifecycleTracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LifecycleTackerActivity : ComponentActivity() {
    private val vm = LifecycleTackerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
        setContent {
            LifecycleTrackerRoot(vm.messageBuffer, lifecycle)
        }
        viewModelStore
    }

    private fun initObserver() {
        val lifecycleTracker = LifecycleTracker()
        lifecycle.addObserver(lifecycleTracker)
        val lifecycleEventTracker = LifecycleEventTracker()
        lifecycle.addObserver(lifecycleEventTracker)

        lifecycle.addObserver(vm)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.messageBuffer.collect {
                    Log.d("LifecycleTackerActivity", "messageBuffer $it")
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    vm.sendMessage("onCreate")
                    Log.d("LifecycleTackerActivity", "onCreate")
                    delay(1000)
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LifecycleTackerActivity::class.java)
        }
    }
}

class LifecycleEventTracker : LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                Log.d("LifecycleEventTracker", "onCreate $source")
            }

            Lifecycle.Event.ON_DESTROY -> {
                Log.d("LifecycleEventTracker", "onDestroy $source")
            }

            else -> {}
        }
    }
}

class LifecycleTracker : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        Log.d("LifecycleTracker", "onCreate $owner")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)

        Log.d("LifecycleTracker", "onStart $owner")
    }
}
