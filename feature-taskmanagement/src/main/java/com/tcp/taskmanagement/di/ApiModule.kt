package com.tcp.taskmanagement.di

import com.tcp.taskmanagement.data.networking.api.TaskApiService
import network.qualifier.TaskService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun bindTaskApi(@TaskService retrofit: Retrofit): TaskApiService =
        retrofit.create(TaskApiService::class.java)
}