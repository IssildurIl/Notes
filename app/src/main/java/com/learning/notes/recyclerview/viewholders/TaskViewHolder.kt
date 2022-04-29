package com.learning.notes.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.learning.notes.databinding.CustomRecyclerviewLayoutBinding
import com.learning.notes.model.Task
import com.learning.notes.model.enums.TaskStatus
import com.learning.notes.recyclerview.ItemListener

class TaskViewHolder(private val binding: CustomRecyclerviewLayoutBinding, itemListener: ItemListener) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            itemListener.onClick(adapterPosition)
        }

        binding.isActive.setOnCheckedChangeListener { _, isChecked ->
            itemListener.onItemCheckedChange(adapterPosition, isChecked)
        }
    }

    fun bind(task: Task) {
        binding.body.text = task.body
        binding.header.text = task.header
        binding.isActive.isChecked = task.isActive()
    }
}