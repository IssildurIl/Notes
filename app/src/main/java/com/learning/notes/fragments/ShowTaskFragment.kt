package com.learning.notes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.learning.notes.R
import com.learning.notes.fragments.taskList.TaskListDirections
import com.learning.notes.model.Task
import com.learning.notes.model.enums.TaskStatus
import com.learning.notes.recyclerview.ItemListener
import com.learning.notes.recyclerview.TaskListAdapter
import com.learning.notes.utils.SwipeGesture
import com.learning.notes.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task_list.view.*

abstract class ShowTaskFragment : Fragment(), ItemListener {

    internal lateinit var taskViewModel: TaskViewModel
    protected var adapter: TaskListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        initRecyclerView(view)
        initTouchLogic(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView(view: View) {
        adapter = TaskListAdapter(this)
        val recyclerView = view.task_recycler_view

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        getObservable().observe(viewLifecycleOwner) { task ->
            adapter!!.submitList(task)
        }
        swipeLogic(adapter!!, recyclerView)
    }

    private fun initTouchLogic(view: View) {
        view.fab.isVisible = showFab()
        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_taskList_to_addTask)
        }
    }

    private fun swipeLogic(adapter: TaskListAdapter, recyclerView: RecyclerView) {
        val swipeGesture = object : SwipeGesture() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        val currentList = adapter.currentList.toMutableList()
                        currentList.removeAt(viewHolder.adapterPosition)
                        adapter.submitList(currentList)
                        taskViewModel.deleteTask(adapter.currentList[viewHolder.adapterPosition])
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onClick(position: Int) {
        adapter?.currentList?.get(position)?.let { value ->
            val action = TaskListDirections.actionTaskListToUpdateTaskFragment(value)
            findNavController().navigate(action)
        }
    }

    override fun onItemCheckedChange(position: Int, checked: Boolean) {
        adapter?.currentList?.get(position)?.let {
            it.status = if (checked) {
                TaskStatus.ARCHIVED
            } else {
                TaskStatus.ACTIVE
            }
            taskViewModel.updateTask(it)
        }
    }

    abstract fun getObservable(): LiveData<List<Task>>

    abstract fun showFab(): Boolean
}