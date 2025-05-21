package com.gowthamraj07.tasklite.repository

import com.gowthamraj07.tasklite.model.Task
import com.gowthamraj07.tasklite.utils.RandomUUIDFactory
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

class SettingsTaskRepository(
    private val settings: Settings
) : TaskRepository {

    private val mutex = Mutex()
    private val key = "tasks"

    private var cachedTasks = loadTasks()

    override suspend fun getTasks(): List<Task> = mutex.withLock {
        cachedTasks
    }

    override suspend fun addTask(title: String) = mutex.withLock {
        val newTask = Task(id = generateUUID(), title = title)
        cachedTasks = cachedTasks + newTask
        saveTasks()
    }

    override suspend fun toggleTask(id: String) = mutex.withLock {
        cachedTasks = cachedTasks.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
        saveTasks()
    }

    override suspend fun deleteTask(id: String) = mutex.withLock {
        cachedTasks = cachedTasks.filterNot { it.id == id }
        saveTasks()
    }

    private fun saveTasks() {
        val json = Json.encodeToString(cachedTasks)
        settings[key] = json
    }

    private fun loadTasks(): List<Task> {
        val json = settings.getStringOrNull(key) ?: return emptyList()
        return try {
            Json.decodeFromString(json)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun generateUUID(): String {
        return RandomUUIDFactory.create()
    }
}