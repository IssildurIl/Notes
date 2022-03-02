package com.learning.notes.data.repository

import androidx.lifecycle.LiveData
import com.learning.notes.model.Task
import com.learning.notes.data.dao.TaskDao
import com.learning.notes.model.enums.TaskStatus

class TaskRepository(private val taskDao: TaskDao) {

    val readAllTasks:LiveData<List<Task>> = taskDao.getAllTasks()
    val activeTasks:LiveData<List<Task>> = taskDao.getTasksByStatus(TaskStatus.ARCHIVED)
    val archivedTasks:LiveData<List<Task>> = taskDao.getTasksByStatus(TaskStatus.ACTIVE)

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