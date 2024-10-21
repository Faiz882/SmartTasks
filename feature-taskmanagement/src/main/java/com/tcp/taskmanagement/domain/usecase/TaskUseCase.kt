package com.tcp.taskmanagement.domain.usecase

import com.tcp.taskmanagement.data.model.LocalTasks
import kotlinx.coroutines.flow.Flow

interface TaskUseCase {
    fun invoke() : Flow<LocalTasks>
}