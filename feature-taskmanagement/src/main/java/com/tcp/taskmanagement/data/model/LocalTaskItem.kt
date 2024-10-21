package com.tcp.taskmanagement.data.model


data class LocalTaskItem(
    val id: String?,
    val description: String?,
    val dueDate: String?,
    val priority: Int?,
    val title: String?,
    val daysLeft: Long?,
)