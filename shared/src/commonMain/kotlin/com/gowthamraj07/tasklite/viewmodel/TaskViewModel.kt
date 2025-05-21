package com.gowthamraj07.tasklite.viewmodel

import com.gowthamraj07.tasklite.model.Task
import com.gowthamraj07.tasklite.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.SupervisorJob

class TaskViewModel(
    private val repository: TaskRepository
) {

    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getTasks()
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(title)
            _tasks.value = repository.getTasks()
        }
    }

    fun toggleTask(id: String) {
        viewModelScope.launch {
            repository.toggleTask(id)
            _tasks.value = repository.getTasks()
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch {
            repository.deleteTask(id)
            _tasks.value = repository.getTasks()
        }
    }
}
