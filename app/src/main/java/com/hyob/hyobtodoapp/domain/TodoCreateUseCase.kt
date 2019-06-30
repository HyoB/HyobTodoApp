package com.hyob.hyobtodoapp.domain

import com.hyob.hyobtodoapp.repository.TodoRepository

class TodoCreateUseCase(
    private val todoRepository: TodoRepository
) {

    suspend fun createTodo(title: String, contents: String) {
        if (title.isEmpty() || contents.isEmpty())
            throw IllegalAccessException("Todo can't be empty")
        todoRepository.createTodo(title, contents)
    }

}