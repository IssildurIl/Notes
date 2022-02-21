package com.learning.notes.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.learning.notes.databinding.CustomRecyclerviewLayoutBinding
import com.learning.notes.model.Task
import com.learning.notes.recyclerview.ItemListener

class TaskViewHolder(private val binding: CustomRecyclerviewLayoutBinding, itemListener: ItemListener) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            itemListener.onClick(adapterPosition)
        }

    }

    fun bind(task: Task) {
        binding.body.text = task.body
        binding.header.text = task.header
    }
}