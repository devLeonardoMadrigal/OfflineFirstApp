package com.example.offlinefirstapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlinefirstapp.entities.TodoItem
import com.example.offlinefirstapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    private val _items = MutableStateFlow<List<TodoItem>>(emptyList())

    val items : StateFlow<List<TodoItem>> = _items.asStateFlow()

    init {
        readAllItems()
    }

    fun readAllItems(){
        viewModelScope.launch{
            repository.getAllItems().collect {
                _items.value = it
            }
        }
    }

    fun deleteItem(item: TodoItem) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun addItem(text: String, isCompleted: Boolean = false){
        viewModelScope.launch {
            repository.insertItem(
                TodoItem(
                    text = text,
                    isCompleted = isCompleted
                )
            )
        }
    }

    fun updateCompletedStatus(item: TodoItem, newStatus: Boolean){
        viewModelScope.launch {
            repository.updateItem(
                item.copy(
                    isCompleted = newStatus
                )
            )
        }
    }

//    fun updateItem(item: TodoItem, newText?: String?, newIsCompletedStatus: Boolean?){
//
//        viewModelScope.launch {
//
//        }
//    }

}