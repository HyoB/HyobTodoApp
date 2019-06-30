package com.hyob.hyobtodoapp.entity

import java.lang.Exception
import java.lang.RuntimeException

data class Todo(
    val id: Long,
    val title: String,
    val content: String,
    val state: State
) {

    enum class State(val isComplete: Boolean) {
        ACTIVE(false), COMPLETE(true)
    }

    companion object {
        fun NEW(id: Long, title: String, content: String, isCompleted: Boolean) =
            Todo(id, title, content, if (isCompleted) State.COMPLETE else State.ACTIVE)
    }

}