package com.tcp.taskmanagement.data.repository

import com.tcp.taskmanagement.data.datasource.TaskDataSource
import com.tcp.taskmanagement.data.model.LocalTasks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDataSource: TaskDataSource
) : TaskRepository {
    override fun getAllTasks(): Flow<LocalTasks> =
        taskDataSource.getALlTasks()

}