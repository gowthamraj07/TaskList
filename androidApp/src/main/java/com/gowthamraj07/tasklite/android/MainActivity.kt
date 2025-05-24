package com.gowthamraj07.tasklite.android

import ChecklistScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gowthamraj07.tasklite.viewmodel.TaskViewModel
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by inject<TaskViewModel>()
                    val tasks by viewModel.tasks.collectAsState()

                    ChecklistScreen(
                        tasks = tasks,
                        onAddTask = viewModel::addTask,
                        onToggleTask = viewModel::toggleTask,
                        onDeleteTask = viewModel::deleteTask
                    )
                }
            }
        }
    }
}