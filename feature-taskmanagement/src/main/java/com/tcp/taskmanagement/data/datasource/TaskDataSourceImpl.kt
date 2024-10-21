package com.tcp.taskmanagement.data.datasource

import com.tcp.taskmanagement.data.model.LocalTasks
import com.tcp.taskmanagement.data.networking.api.TaskApiService
import com.tcp.taskmanagement.data.transformer.transformToLocalTasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TaskDataSourceImpl @Inject constructor(
    private val taskApiService: TaskApiService
) : TaskDataSource {
    override fun getALlTasks(): Flow<LocalTasks> = flow<LocalTasks> {
        val response = taskApiService.getALlTasks().transformToLocalTasks()
        emit(response)
    }
}