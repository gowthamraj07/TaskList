package com.gowthamraj07.tasklite.android.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gowthamraj07.tasklite.model.Task

@Composable
fun ChecklistScreen(
    tasks: List<Task>,
    onAddTask: (String) -> Unit,
    onToggleTask: (String) -> Unit,
    onDeleteTask: (String) -> Unit
) {
    var newTaskText by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("📝 TaskLite", fontSize = 22.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                placeholder = { Text("New Task") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                if (newTaskText.isNotBlank()) {
                    onAddTask(newTaskText)
                    newTaskText = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { onToggleTask(task.id) },
                    onDelete = { onDeleteTask(task.id) }
                )
            }
        }
    }
}
