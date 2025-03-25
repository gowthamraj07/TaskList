package com.gowthamraj07.tasklite.repository

import com.gowthamraj07.tasklite.model.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
    suspend fun addTask(title: String)
    suspend fun toggleTask(id: String)
    suspend fun deleteTask(id: String)
}
