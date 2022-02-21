package com.learning.notes.fragments.addTask

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.learning.notes.R
import com.learning.notes.model.Task
import com.learning.notes.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import java.util.*

class AddTask : Fragment() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        view.addBtn.setOnClickListener {
            insertDataToDB()
        }

        return view
    }

    private fun insertDataToDB() {
        val header = taskHeader.text.toString()
        val body = taskDescription.text.toString()

        if (inputCheck(header, body)) {
            val task = Task(0, header, body, Date().time)
            taskViewModel.addTask(task)
            Toast.makeText(requireContext(), "task added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addTask_to_taskList)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(header: String, body: String): Boolean {
        return !(TextUtils.isEmpty(header) && TextUtils.isEmpty(body))
    }

}