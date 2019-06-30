package com.hyob.hyobtodoapp.repository

import android.util.Log
import com.hyob.hyobtodoapp.db.AppDatabase
import com.hyob.hyobtodoapp.db.table.TodoTable
import com.hyob.hyobtodoapp.entity.TodoMapper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TodoRepository(private val appDatabase: AppDatabase){

    suspend fun getTodos() = withContext(Dispatchers.IO) {
//        IO 디스패쳐 안쓰면 느려
        appDatabase.getTodoDao().selectTodos()
    }

    suspend fun getTodosByActiveFilter(isComplete: Boolean) =
        appDatabase.getTodoDao().selectTodosByFilter(isComplete)

    suspend fun getTodo(id: Long) =
        appDatabase.getTodoDao().selectTodoById(id)

    suspend fun createTodo(title: String, contents: String) =
        appDatabase.getTodoDao().insert(TodoTable.NEW(title, contents))

    suspend fun updateTodo(id: Long, isComplete: Boolean) =
        appDatabase.getTodoDao().updateTodo(id, isComplete)

}
