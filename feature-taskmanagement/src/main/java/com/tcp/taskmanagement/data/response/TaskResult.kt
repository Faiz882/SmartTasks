package com.tcp.taskmanagement.data.response

import com.tcp.taskmanagement.data.model.LocalTasks

sealed class TaskResult {
    data class Success(val localTasks: LocalTasks) : TaskResult()
    data class Error(val exception: Throwable) : TaskResult()
}