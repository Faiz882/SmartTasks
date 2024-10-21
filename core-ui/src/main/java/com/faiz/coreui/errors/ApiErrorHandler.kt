package com.faiz.coreui.errors

import com.faiz.coreui.viewmodel.ViewModelState

interface ApiErrorHandler {
    fun handleError(throwable: Throwable) : ViewModelState.Error
    companion object {
        const val ERROR_CODE_OFFLINE = "offline"
        const val ERROR_CODE_TIMEOUT = "timeout"
    }
}