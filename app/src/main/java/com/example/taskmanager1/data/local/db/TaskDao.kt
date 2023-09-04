package com.example.taskmanager1.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanager1.model.Task

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>

    @Delete
    fun delete(task : Task)

    @Update
    fun update(task:Task)
}