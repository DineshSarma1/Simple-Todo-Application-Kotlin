package com.example.daggerhiltmvvplayouttodoapp.ui.todo_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltmvvplayouttodoapp.DeleteListener
import com.example.daggerhiltmvvplayouttodoapp.R
import com.example.daggerhiltmvvplayouttodoapp.data.Todo
import com.example.daggerhiltmvvplayouttodoapp.databinding.TodoItemBinding

class TodoAdapter(
    private var todoList: MutableList<Todo> = mutableListOf(),
    var deleteLister: DeleteListener
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.todo = todo
            binding.deleteListener = deleteLister
        }
/*
        var tvTitle: TextView
        var tvDescription: TextView
        init {
            tvTitle = view.findViewById(R.id.tv_title)
            tvDescription = view.findViewById(R.id.tv_description)
        }*/
    }

    fun updateList(newTodos: List<Todo>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl<Todo> (todoList, newTodos))
        todoList.clear()
        todoList.addAll(newTodos)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            bind(todoList[position])
        }
    }

    override fun getItemCount(): Int = todoList.size

    //to compare two list while calling the updateList method
    class DiffUtilCallbackImpl<Todo>(val oldList: List<Todo>, val newList: List<Todo>): DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}