package com.learning.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.learning.notes.model.Task
import com.learning.notes.data.TaskDatabase
import com.learning.notes.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<Task>>
    val readActive: LiveData<List<Task>>
    val readArchived: LiveData<List<Task>>
    private val taskRepository: TaskRepository

    init {
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        taskRepository = TaskRepository(taskDao)
        readAllData = taskRepository.readAllTasks
        readActive = taskRepository.activeTasks
        readArchived = taskRepository.archivedTasks
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task)
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteTask(task)
        }
    }
}