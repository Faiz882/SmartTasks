package com.tcp.taskmanagement.ui

import androidx.lifecycle.viewModelScope
import com.faiz.coreui.viewmodel.BaseViewModel
import com.tcp.taskmanagement.data.model.LocalTasks
import com.tcp.taskmanagement.data.response.TaskResult
import com.tcp.taskmanagement.domain.usecase.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUse: TaskUseCase
) : BaseViewModel() {

    private val _taskData = MutableStateFlow<LocalTasks?>(null)
    val taskData = _taskData.asStateFlow()

    fun getAllTasks(){
        viewModelScope.launch {
            taskUse.invoke().justLaunch {
                _taskData.emit(it)
            }
        }

    }
}