package com.hyob.hyobtodoapp.domain

import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.entity.TodoMapper
import com.hyob.hyobtodoapp.repository.TodoRepository
import kotlinx.coroutines.*
import java.lang.RuntimeException

class TodoUseCase(private val todoRepository: TodoRepository) {

    suspend fun getAllTodos(): List<Todo> {
        return todoRepository.getTodos().run {
            if (isEmpty()) throw EmptyListException()
            map(TodoMapper::toEntity)
        }
    }

    suspend fun getActiveTodos(): List<Todo> {
        return todoRepository.getTodosByActiveFilter(false)
            .map(TodoMapper::toEntity)
    }

    suspend fun getCompleteTodos(): List<Todo> {
        return todoRepository.getTodosByActiveFilter(true)
            .map(TodoMapper::toEntity)
    }


    suspend fun createTodo(title: String, contents: String) {
        if (title.isEmpty() || contents.isEmpty())
            throw EmptyException()
        todoRepository.createTodo(title, contents)
    }

    suspend fun getTodoById(id: Long): Todo {
        return todoRepository.getTodo(id).run {
            Todo.NEW(id, title, content, isComplete)
        }
    }

    suspend fun updateTodoState(id: Long, isComplete: Boolean) =
        todoRepository.run {
            updateTodo(id, isComplete)
            getTodo(id).run {
                Todo.NEW(id, title, content, isComplete)
            }
        }

    class EmptyListException : RuntimeException("TodoList is empty")
    class EmptyException : RuntimeException("Todo title or content is empty")

}