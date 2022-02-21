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

    //    private var taskList = emptyList<Task>().toMutableList()
//
//    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
//        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_recyclerview_layout, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
//        val currentItem = taskList[position]
//        holder.itemView.ll.header.text = currentItem.header
//        holder.itemView.ll.body.text = currentItem.body
//
//        holder.itemView.rowLayout.setOnClickListener {
//            val action = TaskListDirections.actionTaskListToUpdateTaskFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun deleteItem(position: Int){
//        taskList.removeAt(position)
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount(): Int {
//        return taskList.size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(taskList: List<Task>) {
//        this.taskList = taskList.toMutableList()
//        notifyDataSetChanged()
//    }

}