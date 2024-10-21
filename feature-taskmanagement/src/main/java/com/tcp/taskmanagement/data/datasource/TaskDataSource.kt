package com.tcp.taskmanagement.data.datasource

import com.tcp.taskmanagement.data.model.LocalTasks
import kotlinx.coroutines.flow.Flow

interface TaskDataSource {
    fun getALlTasks(): Flow<LocalTasks>
}