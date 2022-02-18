package com.learning.notes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.learning.notes.models.Task
import com.learning.notes.room.TaskDatabase
import com.learning.notes.room.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<Task>>
    private val taskRepository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        taskRepository = TaskRepository(taskDao)
        readAllData = taskRepository.readAllTasks
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)
        }
    }
}