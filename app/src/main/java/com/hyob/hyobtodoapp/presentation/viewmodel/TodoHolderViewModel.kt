package com.hyob.hyobtodoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.hyob.hyobtodoapp.domain.TodoUseCase
import com.hyob.hyobtodoapp.entity.Todo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class TodoHolderViewModel(
    private val todo: Todo,
    private val useCase: TodoUseCase
): ViewModel() {

    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->

    }

    fun getIdentifier() = todo.id

    private val _todoLiveData = MutableLiveData<Todo>()
    fun updateTodoView() = _todoLiveData

    init {
        _todoLiveData.postValue(todo)
    }

    fun updateTodo(isComplete: Boolean) {
        scope.launch {
            useCase.updateTodoState(todo.id, isComplete)
        }
    }

}