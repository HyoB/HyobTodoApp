package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.domain.TodoUseCase
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.entity.TodoMapper
import com.hyob.hyobtodoapp.repository.TodoRepository
import kotlinx.coroutines.*
import kotlin.properties.Delegates

class TodoListViewModel(
    private val useCase: TodoUseCase
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.d("TodoListViewModel", "$throwable")
    }

    val scope = viewModelScope + errorHandler

    private val emptyTodos = MutableLiveData<Unit>()
    fun showEmptyTodos() = emptyTodos

    private val todos = MutableLiveData<List<TodoHolderViewModel>>()
    fun showTodos() = todos

    private val errorLiveData = MutableLiveData<Throwable>()

    private var filter by Delegates.observable(TodoFilter.ALL) { _, _, new ->
        scope.launch {
            val todos = when (new) {
                TodoFilter.ACTIVE -> useCase.getActiveTodos()
                TodoFilter.COMPLETE -> useCase.getCompleteTodos()
                TodoFilter.ALL -> useCase.getAllTodos()
            }
            handleTodos(todos)
        }
    }

    init {
        updateTodoFilter(TodoFilter.ALL)
    }

    private fun handleTodos(todos: List<Todo>) {
        todos.map { TodoMapper.toViewModel(it, useCase) }.let { new ->
            this.todos.value = new
        }
    }

    fun updateTodoFilter(filter: TodoFilter) {
        this.filter = filter
    }

    enum class TodoFilter {
        ALL, ACTIVE, COMPLETE
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = TodoRepository(AppDatabase.getInstance(context))
            val useCase = TodoUseCase(repository)
            return TodoListViewModel(useCase) as T
        }
    }

}