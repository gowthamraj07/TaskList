package com.gowthamraj07.tasklite.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val title: String,
    val isDone: Boolean = false
)
