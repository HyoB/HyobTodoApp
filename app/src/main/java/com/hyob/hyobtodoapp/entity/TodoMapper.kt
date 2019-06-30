package com.hyob.hyobtodoapp.entity

import android.util.Log
import com.hyob.hyobtodoapp.db.table.TodoTable
import com.hyob.hyobtodoapp.domain.TodoUseCase
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoHolderViewModel

object TodoMapper {

    fun toEntity(dao: TodoTable) =
        dao.run {
            Log.d("TodoMapper", "$this")
            Todo.NEW(id ?: throw IllegalAccessException("Todo id can't be null"), title, content, isComplete)
        }

    fun toDao(entity: Todo): TodoTable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toViewModel(entity: Todo, useCase: TodoUseCase): TodoHolderViewModel =
        TodoHolderViewModel(entity, useCase)

}