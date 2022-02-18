package com.learning.notes.fragments.taskList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.notes.R
import com.learning.notes.recyclerview.TaskListAdapter
import com.learning.notes.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.fragment_task_list.view.*


class TaskList : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        initRecyclerView(view)
        initTouchLogic(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val adapter = TaskListAdapter()
        val recyclerView = view.task_recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.readAllData.observe(viewLifecycleOwner) { task ->
            adapter.setData(task)
        }
    }

    private fun initTouchLogic(view: View) {
        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_taskList_to_addTask)
        }

    }

}