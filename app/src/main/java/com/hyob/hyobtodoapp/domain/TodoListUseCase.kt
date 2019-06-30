package com.hyob.hyobtodoapp.domain

import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.entity.TodoMapper
import com.hyob.hyobtodoapp.repository.TodoRepository
import kotlinx.coroutines.*

class TodoListUseCase(private val todoRepository: TodoRepository) {

    suspend fun getTodos() =
        todoRepository.getTodos()
            .map(TodoMapper::toEntity)


    suspend fun getActiveTodos() =
        todoRepository.getTodosByActiveFilter(false)
            .map(TodoMapper::toEntity)


    suspend fun getCompleteTodos() =
        todoRepository.getTodosByActiveFilter(true)
            .map(TodoMapper::toEntity)


    suspend fun updateTodoState(id: Long, isComplete: Boolean) =
        todoRepository.run {
            updateTodo(id, isComplete)
            getTodo(id).run {
                Todo.NEW(id, title, content, isComplete)
            }
        }

}