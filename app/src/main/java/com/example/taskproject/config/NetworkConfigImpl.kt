package com.example.taskproject.config

import com.example.taskproject.BuildConfig
import network.config.NetworkConfig
import javax.inject.Inject

class NetworkConfigImpl @Inject constructor() : NetworkConfig {
    override var apiBaseUrl:String = BuildConfig.apiBaseUrl
}