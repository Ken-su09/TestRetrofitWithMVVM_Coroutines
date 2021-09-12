package com.suonk.testretrofit2.repositories

import com.suonk.testretrofit2.api.TodoApiService
import com.suonk.testretrofit2.models.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class TodoRepository constructor(private val todoApiService: TodoApiService) {

    suspend fun getTodos() = todoApiService.getTodos()

    companion object {
        @Volatile
        private var instance: TodoRepository? = null

        fun getInstance(todoApiService: TodoApiService) = instance ?: synchronized(this) {
            instance ?: TodoRepository(todoApiService).also {
                instance = it
            }
        }
    }
}