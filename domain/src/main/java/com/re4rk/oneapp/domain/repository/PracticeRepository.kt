package com.re4rk.oneapp.domain.repository

import com.re4rk.oneapp.domain.model.Practice
import kotlinx.coroutines.flow.Flow

interface PracticeRepository {
    fun getPractices(): Flow<List<Practice>>
    fun addPractice(practice: Practice)

    fun deletePractice(practice: Practice)
}
