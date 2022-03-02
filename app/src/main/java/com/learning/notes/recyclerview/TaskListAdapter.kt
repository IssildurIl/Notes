package com.learning.notes.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.learning.notes.databinding.CustomRecyclerviewLayoutBinding
import com.learning.notes.model.Task
import com.learning.notes.recyclerview.viewholders.TaskViewHolder

class TaskListAdapter(private val itemListener: ItemListener) : ListAdapter<Task, TaskViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val label = getItem(position)
        holder.bind(label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CustomRecyclerviewLayoutBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding, itemListener)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Task>() {

        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }

}