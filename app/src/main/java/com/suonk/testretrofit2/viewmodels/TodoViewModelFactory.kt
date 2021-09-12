package com.suonk.testretrofit2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suonk.testretrofit2.repositories.TodoRepository

class TodoViewModelFactory constructor(private val todoRepository: TodoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(todoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}