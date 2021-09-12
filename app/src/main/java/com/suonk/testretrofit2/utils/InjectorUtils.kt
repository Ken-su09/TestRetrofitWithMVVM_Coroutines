package com.suonk.testretrofit2.utils

import com.suonk.testretrofit2.api.TodoApiService
import com.suonk.testretrofit2.repositories.TodoRepository
import com.suonk.testretrofit2.viewmodels.TodoViewModelFactory

object InjectorUtils {

    fun provideTodoViewModelFactory(): TodoViewModelFactory {
        val repository = TodoRepository.getInstance(TodoApiService.getInstance())
        return TodoViewModelFactory(repository)
    }
}