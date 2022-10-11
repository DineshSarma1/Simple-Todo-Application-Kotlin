package com.example.daggerhiltmvvplayouttodoapp.ui.todo_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltmvvplayouttodoapp.DeleteListener
import com.example.daggerhiltmvvplayouttodoapp.R
import com.example.daggerhiltmvvplayouttodoapp.data.Todo
import com.example.daggerhiltmvvplayouttodoapp.databinding.FragmentHomeBinding
import org.w3c.dom.Text


class HomeFragment : Fragment(R.layout.fragment_home) , DeleteListener{

    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TodoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        initViewModel()

    }

    fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]

        //to pass the control in view model(ie. For click listener)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //Observing the list of data and updating the adapter with live data
        viewModel.getRecordObserver().observe(viewLifecycleOwner) {
                t -> t?.let { adapter.updateList(it) }
        }

    }

    fun initRecyclerView() {

        //initializing adapter with click lister
        adapter = TodoAdapter(deleteLister= this)

        //setting adapter into recycler view
        binding.todoListRecyclerView.adapter = adapter
    }

    //handling delete click listener
    override fun onDeleteIconClicked(todo: Todo) {
        viewModel.deleteTodo(todo)
    }
}