package com.re4rk.oneapp.presentation.ui.home

import android.content.Intent

sealed interface HomeState {
    data class Success(val contents: List<Content>) : HomeState
    data class Error(val error: Throwable?) : HomeState
    object Loading : HomeState
}

data class Content(
    val intent: Intent,
    val description: String,
)
