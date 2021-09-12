package com.suonk.testretrofit2.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suonk.testretrofit2.models.Todo
import com.suonk.testretrofit2.databinding.ItemToDoBinding

class TodoAdapter : ListAdapter<Todo, TodoAdapter.TodoViewHolder>(TodoComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemToDoBinding.inflate(LayoutInflater.from(parent.context))
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }

    inner class TodoViewHolder(private val binding: ItemToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.apply {
                title.text = todo.title
                checkbox.isChecked = todo.completed
            }
        }
    }

    class TodoComparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.title == newItem.title
        }
    }
}