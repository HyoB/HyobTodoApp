package com.hyob.hyobtodoapp.presentation.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.*
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.domain.TodoUseCase
import com.hyob.hyobtodoapp.repository.TodoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class TodoCreateViewModel(
    private val useCase: TodoUseCase
): ViewModel() {

    private val scope = viewModelScope + CoroutineExceptionHandler { _, throwable ->
        _viewStateLiveData.postValue(
            when (throwable) {
                is TodoUseCase.EmptyException -> ViewState.EMPTY
                else -> ViewState.UNKNOWN
            }
        )
    }

    private val _viewStateLiveData = MutableLiveData<ViewState>()
    fun viewStateUpdated(): LiveData<ViewState> = _viewStateLiveData

    fun confirmButtonClick(title: String, contents: String) {
        scope.launch {
            useCase.createTodo(title, contents)
            _viewStateLiveData.postValue(ViewState.COMPLETE)
        }
    }

    enum class ViewState {
        EMPTY, COMPLETE, UNKNOWN
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val context: Context): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val repository = TodoRepository(AppDatabase.getInstance(context))
            val useCase = TodoUseCase(repository)
            return TodoCreateViewModel(useCase) as T
        }
    }

}