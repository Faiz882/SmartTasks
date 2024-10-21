package com.tcp.taskmanagement.di

import com.tcp.taskmanagement.data.datasource.TaskDataSource
import com.tcp.taskmanagement.data.datasource.TaskDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindTaskDataSource(impl: TaskDataSourceImpl): TaskDataSource
}