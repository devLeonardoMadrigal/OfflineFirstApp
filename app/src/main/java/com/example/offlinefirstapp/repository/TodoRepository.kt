package com.example.offlinefirstapp.repository

import com.example.offlinefirstapp.dao.TodoItemDAO
import com.example.offlinefirstapp.entities.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: TodoItemDAO) {

    fun getAllItems() : Flow<List<TodoItem>> = dao.getAllItems()

    suspend fun insertItem(item: TodoItem) = dao.insertItem(item)

    suspend fun updateItem(item: TodoItem) = dao.updateItem(item)

    suspend fun deleteItem(item: TodoItem) = dao.deleteItem(item)

}