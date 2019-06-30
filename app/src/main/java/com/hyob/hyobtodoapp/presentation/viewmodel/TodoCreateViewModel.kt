package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.db.table.TodoTable
import com.hyob.hyobtodoapp.domain.TodoCreateUseCase
import com.hyob.hyobtodoapp.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoCreateViewModel(
    private val useCase: TodoCreateUseCase
): ViewModel() {

    private val complete = MutableLiveData<Unit>()
    fun completeTodoCreate() = complete

    fun confirmButtonClick(title: String, contents: String) = with(useCase){
//        createTodo(title, contents)
//        complete.postValue(Unit)
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val context: Context): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = TodoRepository(AppDatabase.getInstance(context))
            val useCase = TodoCreateUseCase(repository)
            return TodoCreateViewModel(useCase) as T
        }
    }

}