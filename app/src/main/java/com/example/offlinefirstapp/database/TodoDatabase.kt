package com.example.offlinefirstapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.offlinefirstapp.dao.TodoItemDAO
import com.example.offlinefirstapp.entities.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoItemDao() : TodoItemDAO
}
