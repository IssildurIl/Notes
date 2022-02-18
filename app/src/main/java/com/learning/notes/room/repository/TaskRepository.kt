package com.learning.notes.room.repository

import androidx.lifecycle.LiveData
import com.learning.notes.models.Task
import com.learning.notes.room.dao.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    val readAllTasks:LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
}