package com.tcp.taskmanagement.data.networking.api

import com.tcp.taskmanagement.data.response.Tasks
import retrofit2.http.GET
import retrofit2.http.Url

interface TaskApiService {

    @GET("/")
    suspend fun getALlTasks() : Tasks
}