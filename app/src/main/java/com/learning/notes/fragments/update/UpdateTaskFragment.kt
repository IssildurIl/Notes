package com.learning.notes.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.learning.notes.R
import com.learning.notes.model.Task
import com.learning.notes.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update_task.view.*
import java.util.*

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_task, container, false)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        init(view)
        return view
    }

    private fun init(view: View) {
        val header = args.currentTask.header
        val body = args.currentTask.body
        view.updTaskHeader.setText(header)
        view.updTaskDescription.setText(body)

        view.updBtn.setOnClickListener {
            if (!checkChanges(header, body, view)) {
                updateTask(view)
            }
            findNavController().navigate(R.id.action_updateTaskFragment_to_taskList)
        }
    }


    private fun checkChanges(header: String, body: String, view: View): Boolean {
        return (header == view.updTaskHeader.text.toString() && body == view.updTaskDescription.text.toString())
    }

    private fun inputCheck(header: String, body: String): Boolean {
        return !(TextUtils.isEmpty(header) && TextUtils.isEmpty(body))
    }

    private fun updateTask(view: View) {
        val header = view.updTaskHeader.text.toString()
        val body = view.updTaskDescription.text.toString()
        val task = Task(id = args.currentTask.id, header = header, body = body, timestamp = Date().time)
        if (inputCheck(header, body)) {
            taskViewModel.updateTask(task)
        }
    }
}