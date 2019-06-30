package com.hyob.hyobtodoapp.db.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hyob.hyobtodoapp.db.DateConverter
import com.hyob.hyobtodoapp.db.table.TodoTable.Companion.TODO_TABLE_NAME
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = TODO_TABLE_NAME)
data class TodoTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = TODO_ID_COLUMN_NAME) val id: Long?,
    @ColumnInfo(name = TODO_TITLE_COLUMN_NAME) val title: String,
    @ColumnInfo(name = TODO_CONTENT_COLUMN_NAME) val content: String,
    @ColumnInfo(name = TODO_IS_COMPLETE_COLUMN_NAME) val isComplete: Boolean
) {
    companion object {
        const val TODO_TABLE_NAME = "todo_table"
        const val TODO_ID_COLUMN_NAME = "todo_id"
        const val TODO_TITLE_COLUMN_NAME = "todo_title"
        const val TODO_CONTENT_COLUMN_NAME = "todo_content"
        const val TODO_IS_COMPLETE_COLUMN_NAME = "todo_is_complete"

        fun NEW(title: String, contents: String) =
            TodoTable(null, title, contents, false)

    }
}