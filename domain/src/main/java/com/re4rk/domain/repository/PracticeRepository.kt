package com.re4rk.domain.repository

import com.re4rk.domain.model.Practice
import kotlinx.coroutines.flow.Flow

interface PracticeRepository {
    fun getPractices(): Flow<List<Practice>>
    fun addPractice(practice: Practice)

    fun deletePractice(practice: Practice)
}
