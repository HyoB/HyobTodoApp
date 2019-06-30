package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.domain.TodoUseCase
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.repository.TodoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class TodoDetailViewModel(
    private val todoId: Long,
    private val useCase: TodoUseCase
): ViewModel() {

    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->

    }

    private val _todoLiveData = MutableLiveData<Todo>()
    fun updateTodoView() = _todoLiveData

    init {
        Log.d("TodoDetailViewModel", "$todoId")
        getTodo()
    }

    private fun getTodo() {
        scope.launch {
            _todoLiveData.postValue(useCase.getTodoById(todoId))
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val context: Context, private val todoId: Long) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = TodoRepository(AppDatabase.getInstance(context))
            val useCase = TodoUseCase(repository)
            return TodoDetailViewModel(todoId, useCase) as T
        }
    }

}