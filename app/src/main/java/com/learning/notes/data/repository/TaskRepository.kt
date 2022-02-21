package com.learning.notes.data.repository

import androidx.lifecycle.LiveData
import com.learning.notes.model.Task
import com.learning.notes.data.dao.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val readAllTasks:LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }
}