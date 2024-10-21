package com.tcp.taskmanagement.data.repository

import com.tcp.taskmanagement.data.model.LocalTasks
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasks() : Flow<LocalTasks>
}