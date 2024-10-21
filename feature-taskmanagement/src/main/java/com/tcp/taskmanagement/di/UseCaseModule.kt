package com.tcp.taskmanagement.di

import com.tcp.taskmanagement.domain.usecase.TaskUseCase
import com.tcp.taskmanagement.domain.usecase.TaskUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindTaskUseCase(impl: TaskUseCaseImpl): TaskUseCase
}