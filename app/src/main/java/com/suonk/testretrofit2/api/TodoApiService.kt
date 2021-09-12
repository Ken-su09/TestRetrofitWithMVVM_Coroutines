package com.suonk.testretrofit2.api

import com.suonk.testretrofit2.models.Todo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApiService {

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

    companion object {

        var todoApiService: TodoApiService? = null

        fun getInstance(): TodoApiService {

            if (todoApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                todoApiService = retrofit.create(TodoApiService::class.java)
            }
            return todoApiService!!
        }
    }
}
