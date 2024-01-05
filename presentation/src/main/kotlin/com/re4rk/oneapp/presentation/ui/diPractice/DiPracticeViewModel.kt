package com.re4rk.presentation.ui.diPractice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.domain.model.Practice
import com.re4rk.domain.repository.PracticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiPracticeViewModel @Inject constructor(
    private val practiceRepository: PracticeRepository,
) : ViewModel() {
    val practices: StateFlow<List<Practice>> = practiceRepository.getPractices().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

    fun addPractice(practice: Practice) {
        practiceRepository.addPractice(practice)
    }

    fun deletePractice(practice: Practice) {
        practiceRepository.deletePractice(practice)
    }
}
