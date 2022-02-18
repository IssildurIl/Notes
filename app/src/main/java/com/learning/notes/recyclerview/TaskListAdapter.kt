package com.learning.notes.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.notes.R
import com.learning.notes.models.Task
import kotlinx.android.synthetic.main.custom_recyclerview_layout.view.*

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.RecyclerViewHolder>() {

    private var taskList = emptyList<Task>()

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_recyclerview_layout,parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = taskList[position]
        holder.itemView.ll.header.text = currentItem.header
        holder.itemView.ll.body.text = currentItem.body
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun setData(taskList: List<Task>){
        this.taskList = taskList
        notifyDataSetChanged()
    }
}