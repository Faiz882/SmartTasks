package com.tcp.taskmanagement.domain.usecase

import com.tcp.taskmanagement.data.repository.TaskRepository
import com.tcp.taskmanagement.data.model.LocalTasks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskUseCaseImpl @Inject constructor(
    private val taskRepository: TaskRepository
) : TaskUseCase {
    override fun invoke(): Flow<LocalTasks> =
        taskRepository.getAllTasks()

}