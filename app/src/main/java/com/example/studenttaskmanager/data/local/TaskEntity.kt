package com.example.studenttaskmanager.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.studenttaskmanager.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
)

fun TaskEntity.toTask() = Task(
    id = id,
    title = title,
    isDone = isDone,
)

fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    isDone = isDone,
)
