package com.re4rk.data.repository

import com.re4rk.domain.model.Practice
import com.re4rk.domain.repository.PracticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PracticeInMemoryRepository : PracticeRepository {
    private val practices = MutableList<Practice>(20) { Practice() }

    override fun getPractices(): Flow<List<Practice>> = flow {
        emit(practices.toList())
    }

    override fun addPractice(practice: Practice) {
        practices.add(practice)
    }

    override fun deletePractice(practice: Practice) {
        practices.remove(practice)
    }
}
