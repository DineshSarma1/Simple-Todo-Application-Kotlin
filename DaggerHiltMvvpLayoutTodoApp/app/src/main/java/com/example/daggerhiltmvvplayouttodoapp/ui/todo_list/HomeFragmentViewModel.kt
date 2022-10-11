package com.example.daggerhiltmvvplayouttodoapp.ui.todo_list

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltmvvplayouttodoapp.data.Todo
import com.example.daggerhiltmvvplayouttodoapp.data.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor (
    private val repository: TodoRepository
): ViewModel() {

    private val TAG = HomeFragmentViewModel::class.simpleName

    private var todoData: MutableLiveData<List<Todo>> = MutableLiveData()

    init {
        loadRecords()
    }

    //will be initialized with layout value
    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    fun onSaveButtonClicked() {
        val todo = Todo(title.value.toString(), description.value.toString(), true)
        title.value = ""
        description.value = ""
        insertTodo(todo)
    }


    fun getRecordObserver() : MutableLiveData<List<Todo>> {
        return todoData
    }

    fun loadRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            var list = repository.getTodos()
            todoData.postValue(list)
        }

    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTodo(todo)
            loadRecords()
        }

    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
            loadRecords()
        }

    }

    fun getTodoById(id: Int): Todo? {
        return repository.getTodoById(id)
    }

    fun getAllTodos() : MutableList<Todo> {
        var todos: MutableList<Todo>? = null
//        viewModelScope.launch(Dispatchers.IO) {
//            return@launch repository.getTodos()
//        }
        return todos!!
    }

}