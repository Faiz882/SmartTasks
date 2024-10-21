package com.example.taskproject.di

import com.example.taskproject.config.NetworkConfigImpl
import com.example.taskproject.manager.ApiErrorHandlerImpl
import com.faiz.coreui.errors.ApiErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import network.config.NetworkConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {

    @Binds
    @Singleton
    abstract fun bindAppErrorManager(apiErrorManagerImpl: ApiErrorHandlerImpl): ApiErrorHandler

    @Binds
    @Singleton
    abstract fun bindNetworkConfig(networkConfigImpl : NetworkConfigImpl) : NetworkConfig
}