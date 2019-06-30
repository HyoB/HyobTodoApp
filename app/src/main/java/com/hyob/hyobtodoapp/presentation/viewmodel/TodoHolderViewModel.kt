package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.domain.TodoListUseCase
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.repository.TodoRepository

class TodoHolderViewModel(
    val todo: Todo,
    val useCase: TodoListUseCase
) {

    fun getId() = todo.id

    fun initView(body: (Todo) -> Unit) {
        body.invoke(todo)
    }

    inline fun updateTodoState(isComplete: Boolean, body: (Todo) -> Unit) {
//        useCase.updateTodoState(todo.id, isComplete).let { new ->
//            Log.d("TodoHolderViewModel", "$new")
//            body.invoke(new)
//        }
    }

    fun onClear() {

    }

}