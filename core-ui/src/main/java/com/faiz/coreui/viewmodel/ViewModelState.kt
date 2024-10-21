package com.faiz.coreui.viewmodel

sealed class ViewModelState {
    object Idle : ViewModelState()
    data class Loading(val progress: ProgressType) : ViewModelState()
    data class Error(
        val code: String? = null,
        val error: String,
        var shouldNotify: Boolean = false
    ) : ViewModelState()
}