package com.learning.notes.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.learning.notes.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("Select * from Task Order by id asc")
    fun getAllTasks(): LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

}