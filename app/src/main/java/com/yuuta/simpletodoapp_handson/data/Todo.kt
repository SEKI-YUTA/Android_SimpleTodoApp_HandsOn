package com.yuuta.simpletodoapp_handson.data

import java.util.UUID

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    val text: String,
    val isDone: Boolean = false,
)
