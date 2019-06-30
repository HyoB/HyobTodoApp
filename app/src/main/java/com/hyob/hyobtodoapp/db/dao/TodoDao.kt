package com.hyob.hyobtodoapp.db.dao

import androidx.room.*
import com.hyob.hyobtodoapp.db.table.TodoTable
import com.hyob.hyobtodoapp.db.table.TodoTable.Companion.TODO_ID_COLUMN_NAME
import com.hyob.hyobtodoapp.db.table.TodoTable.Companion.TODO_IS_COMPLETE_COLUMN_NAME
import com.hyob.hyobtodoapp.db.table.TodoTable.Companion.TODO_TABLE_NAME

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(diaryDto: TodoTable)

    @Query("SELECT * FROM $TODO_TABLE_NAME")
    suspend fun selectTodos(): List<TodoTable>

    @Query("SELECT * FROM $TODO_TABLE_NAME WHERE $TODO_IS_COMPLETE_COLUMN_NAME = :isComplete")
    suspend fun selectTodosByFilter(isComplete: Boolean): List<TodoTable>

    @Query("SELECT * FROM $TODO_TABLE_NAME WHERE $TODO_ID_COLUMN_NAME = :id")
    suspend fun selectTodoById(id: Long): TodoTable

    @Query("DELETE FROM $TODO_TABLE_NAME WHERE $TODO_ID_COLUMN_NAME = :id")
    suspend fun deleteTodo(id: Long)

    @Query("UPDATE $TODO_TABLE_NAME SET $TODO_IS_COMPLETE_COLUMN_NAME = :isComplete WHERE $TODO_ID_COLUMN_NAME = :id")
    suspend fun updateTodo(id: Long, isComplete: Boolean)

//    @Update
//    suspend fun updateTodo(id: Long, isComplete: Boolean)
//

}