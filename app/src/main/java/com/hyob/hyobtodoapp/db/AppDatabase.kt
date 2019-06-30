package com.hyob.hyobtodoapp.db

import android.content.Context
import androidx.room.*
import com.hyob.hyobtodoapp.db.dao.TodoDao
import com.hyob.hyobtodoapp.db.table.TodoTable

@Database(entities = [TodoTable::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {

        private const val APP_DATA_BASE_NAME = "app_database.db"

        private var instance : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        APP_DATA_BASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

    }

}