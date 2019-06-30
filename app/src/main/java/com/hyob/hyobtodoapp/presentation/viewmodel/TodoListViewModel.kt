package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.db.table.TodoTable
import com.hyob.hyobtodoapp.domain.TodoListUseCase
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.entity.TodoMapper
import com.hyob.hyobtodoapp.repository.TodoRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.properties.Delegates

class TodoListViewModel(
    private val useCase: TodoListUseCase
) : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->

    }

    val c = viewModelScope + errorHandler

    private val emptyTodos = MutableLiveData<Unit>()

    fun showEmptyTodos() = emptyTodos
    private val todos = MutableLiveData<List<TodoHolderViewModel>>()

    fun showTodos() = todos
    private val errorLiveData = MutableLiveData<Throwable>()

    private var filter by Delegates.observable(TodoFilter.ALL) { _, old, new ->
        viewModelScope.launch(errorHandler) {
            val todos = when (new) {
                TodoFilter.ACTIVE -> {
                        useCase.getActiveTodos()
                }
                TodoFilter.COMPLETE -> {
                        useCase.getCompleteTodos()
                }
                TodoFilter.ALL -> {
                        useCase.getTodos()
                }
            }
            handleTodos(todos)
        }
    }

    init {
//        handleTodos(useCase.getTodos())
    }

    private fun handleTodos(todos: List<Todo>) {
        if (todos.isEmpty())
            emptyTodos.value = Unit
        else {
            todos.map { TodoMapper.toViewModel(it, useCase) }.let { new ->
                this.todos.value = new
            }
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
            val useCase = TodoListUseCase(repository)
            return TodoListViewModel(useCase) as T
        }
    }

}