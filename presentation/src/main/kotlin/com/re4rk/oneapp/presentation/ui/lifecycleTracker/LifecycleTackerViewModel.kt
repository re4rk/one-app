package com.re4rk.presentation.ui.lifecycleTracker

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LifecycleTackerViewModel : DefaultLifecycleObserver {

    private val coroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.Main + SupervisorJob()
    }

    private val _messageBuffer = MutableStateFlow<List<String>>(emptyList())
    val messageBuffer: StateFlow<List<String>> get() = _messageBuffer

    override fun onCreate(owner: LifecycleOwner) {
        coroutineScope.launch {}
        Log.d("LifecycleTrackerViewMOdel", "onCreate $owner")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        coroutineScope.coroutineContext.cancel()
    }

    fun sendMessage(message: String) {
        _messageBuffer.value = _messageBuffer.value + message
    }
}
