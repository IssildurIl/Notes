package com.learning.notes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.learning.notes.model.Task
import com.learning.notes.model.enums.TaskStatus

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("Select * from Task Order by id asc")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("Select * from Task where status = :status Order by id asc")
    fun getTasksByStatus(status:TaskStatus): LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

}