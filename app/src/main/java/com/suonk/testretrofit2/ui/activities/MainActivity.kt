package com.suonk.testretrofit2.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.suonk.testretrofit2.databinding.ActivityMainBinding
import com.suonk.testretrofit2.ui.adapters.TodoAdapter
import com.suonk.testretrofit2.utils.InjectorUtils
import com.suonk.testretrofit2.viewmodels.TodoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        initializeUI()
        retrofitCall()
    }

    private fun initializeUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.recyclerViewToDo.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun retrofitCall() {
        viewModel.getTodos()

        viewModel.listOfTodos.observe(this, { todos ->
            todoAdapter.submitList(todos)
        })

        viewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, { loadingIsVisible ->
            binding.progressBar.isVisible = loadingIsVisible
        })
    }

    private fun setUpViewModel() {
        val factory = InjectorUtils.provideTodoViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[TodoViewModel::class.java]
    }
}