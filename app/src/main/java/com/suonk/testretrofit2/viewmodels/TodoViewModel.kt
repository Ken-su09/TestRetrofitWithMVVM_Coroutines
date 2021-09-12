package com.suonk.testretrofit2.viewmodels

import androidx.lifecycle.*
import com.suonk.testretrofit2.models.Todo
import com.suonk.testretrofit2.repositories.TodoRepository
import kotlinx.coroutines.*
import retrofit2.Response

class TodoViewModel constructor(private val todoRepository: TodoRepository) : ViewModel() {

    var job: Job? = null
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    val listOfTodos = MutableLiveData<List<Todo>>()

    fun getTodos() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            if (isActive) {
                val response = todoRepository.getTodos()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        listOfTodos.postValue(response.body())
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()}")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}