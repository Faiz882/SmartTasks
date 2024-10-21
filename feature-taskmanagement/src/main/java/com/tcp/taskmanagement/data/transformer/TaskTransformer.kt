package com.tcp.taskmanagement.data.transformer

import com.tcp.taskmanagement.data.model.LocalTaskItem
import com.tcp.taskmanagement.data.model.LocalTasks
import com.tcp.taskmanagement.data.response.TaskItem
import com.tcp.taskmanagement.data.response.Tasks
import java.text.SimpleDateFormat
import java.util.Locale

private fun transformToLocalTaskItem(serverTaskItem: TaskItem?): LocalTaskItem? {
    return serverTaskItem?.let {
        LocalTaskItem(
            description = it.description,
            dueDate = it.dueDate,
            priority = it.priority,
            title = it.title,
            id = it.id,
            daysLeft = calculateDaysLeft(it.targetDate,it.dueDate)
        )
    }
}

fun Tasks.transformToLocalTasks(): LocalTasks {
    val localTaskItems = this.tasks?.map { taskItem ->
        transformToLocalTaskItem(taskItem)
    }
    return LocalTasks(tasks = localTaskItems ?: emptyList())
}

fun calculateDaysLeft(targetDateString: String?, dueDateString: String?): Long? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return try {
        val targetDate = targetDateString?.let { dateFormat.parse(it) }
        val dueDate = dueDateString?.let { dateFormat.parse(it) }

        val diffInMillis = dueDate?.time?.minus(targetDate?.time ?: 0L)

        diffInMillis?.div((1000 * 60 * 60 * 24))
    } catch (e: Exception) {
        0L // Handle parse exception
    }
}