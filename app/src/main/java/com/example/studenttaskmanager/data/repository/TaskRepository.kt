package com.example.studenttaskmanager.data.repository

import com.example.studenttaskmanager.data.local.TaskDao
import com.example.studenttaskmanager.data.local.TaskEntity
import com.example.studenttaskmanager.data.local.toTask
import com.example.studenttaskmanager.model.Task
import kotlinx.coroutines.flow.*

class TaskRepository(private val dao: TaskDao) {
    val tasks: Flow<List<Task>> = dao.getAll().map { list -> list.map { it.toTask() } }

    suspend fun add(title: String) = dao.insert(TaskEntity(title = title))

    suspend fun setDone(id: Int, done: Boolean) = dao.setDone(id, done)

    suspend fun delete(id: Int) = dao.deleteById(id)
}
