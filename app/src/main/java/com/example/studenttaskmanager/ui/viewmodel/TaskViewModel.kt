package com.example.studenttaskmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studenttaskmanager.data.repository.TaskRepository
import com.example.studenttaskmanager.model.Task
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TaskViewModel(private val repo: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task>> = repo.tasks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addTask(title: String) {
        viewModelScope.launch { repo.add(title.trim()) }
    }

    fun toggle(id: Int) {
        val task = tasks.value.firstOrNull { it.id == id } ?: return
        viewModelScope.launch { repo.setDone(id, !task.isDone) }
    }

    fun delete(id: Int) {
        viewModelScope.launch { repo.delete(id) }
    }
}
