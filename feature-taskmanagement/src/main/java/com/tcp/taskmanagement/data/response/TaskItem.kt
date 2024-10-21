package com.tcp.taskmanagement.data.response

import com.google.gson.annotations.SerializedName

data class TaskItem(
    @SerializedName("Description")
    val description: String?= null,
    @SerializedName("DueDate")
    val dueDate: String?= null,
    @SerializedName("Priority")
    val priority: Int?= null,
    @SerializedName("TargetDate")
    val targetDate: String?= null,
    @SerializedName("Title")
    val title: String?= null,
    val id: String?= null
)