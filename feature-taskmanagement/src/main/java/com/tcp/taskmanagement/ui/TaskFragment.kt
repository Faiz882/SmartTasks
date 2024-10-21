package com.tcp.taskmanagement.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.tcp.taskmanagement.ui.adapter.TaskAdapter
import com.faiz.coreui.fragment.BaseFragment
import com.tcp.taskmanagement.data.model.LocalTasks
import com.tcp.taskmanagement.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding, TaskViewModel>({ FragmentTaskBinding.inflate(it) })  {

    private val taskAdapter = TaskAdapter()
    override fun onFragmentCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        collectState()
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModel.getAllTasks()
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.taskData.collectLatest {
                handleTaskState(it ?: return@collectLatest)
            }
        }
    }

    private fun handleTaskState(it: LocalTasks) {
        if (it.tasks.isNullOrEmpty()) {
            viewBinding().rvTask.visibility = View.GONE
            viewBinding().tvNoTask.visibility = View.VISIBLE
            viewBinding().ivEmpty.visibility = View.VISIBLE

        } else {
            taskAdapter.submitList(it.tasks)
        }
    }

    private fun initUI() {
        viewBinding().apply {
            rvTask.adapter = taskAdapter
        }
    }

}
