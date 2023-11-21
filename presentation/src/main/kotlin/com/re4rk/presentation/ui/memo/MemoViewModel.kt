package com.re4rk.presentation.ui.memo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.re4rk.domain.model.Memo
import com.re4rk.domain.repository.MemoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val memoRepository: MemoRepository,
) : ViewModel() {
    val memo: StateFlow<List<Memo>> = memoRepository.getMemos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun addMemo(string: String) {
        viewModelScope.launch {
            memoRepository.insertMemo(Memo(title = string, content = string))
        }
    }

    fun deleteMemo(id: Int) {
        viewModelScope.launch {
            memoRepository.deleteMemo(id)
        }
    }
}
