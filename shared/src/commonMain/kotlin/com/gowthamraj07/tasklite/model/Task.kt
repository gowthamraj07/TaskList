package com.gowthamraj07.tasklite.model

data class Task(
    val id: String,
    val title: String,
    val isDone: Boolean = false
)
