package com.tcp.taskmanagement.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tcp.taskmanagement.data.model.LocalTaskItem
import com.tcp.taskmanagement.databinding.TaskRowBinding

class TaskAdapter :
    ListAdapter<LocalTaskItem, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    inner class TaskViewHolder(private val binding: TaskRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(taskItem: LocalTaskItem) {
            val daysLeft = taskItem.daysLeft
            binding.tvTaskTitle.text = taskItem.title
            binding.tvDueDate.text = taskItem.dueDate
            binding.tvDaysLeft.text = if (daysLeft != null) taskItem.daysLeft.toString() else 0.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskRowBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentLocation = getItem(position)
        holder.bind(currentLocation)
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<LocalTaskItem>() {
    override fun areItemsTheSame(oldItem: LocalTaskItem, newItem: LocalTaskItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocalTaskItem, newItem: LocalTaskItem): Boolean {
        return oldItem == newItem
    }
}