package com.hyob.hyobtodoapp.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.*
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoCreateViewModel
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoListViewModel
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoListViewModel.TodoFilter
import kotlinx.android.synthetic.main.fragment_todo_create.*
import kotlinx.android.synthetic.main.toolbar_todo_home.*

class TodoCreateFragment : Fragment() {

    private val todoCreateVm by lazy {
        ViewModelProviders
            .of(this, TodoCreateViewModel.Factory(requireContext()))
            .get(TodoCreateViewModel::class.java)
    }

    private val todoListVm by lazy {
        ViewModelProviders
            .of(requireActivity(), TodoListViewModel.Factory(requireContext()))
            .get(TodoListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(toolbar)
        setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

        fabConfirmTodo.setOnClickListener {
            todoCreateVm.confirmButtonClick(editTodoTitle.text(), editTodoContents.text())
        }

        with(todoCreateVm) {
            viewStateUpdated().observe(this@TodoCreateFragment, Observer {
                when (it) {
                    TodoCreateViewModel.ViewState.COMPLETE -> complete()
                    TodoCreateViewModel.ViewState.EMPTY -> empty()
                    TodoCreateViewModel.ViewState.UNKNOWN -> unknown()
                }
            })
        }
    }

    private fun complete() {
        todoListVm.updateTodoFilter(TodoFilter.ALL)
        hideKeyboard()
        findNavController().popBackStack()
    }

    private fun empty() {
        Toast.makeText(context, "EMPTY!", Toast.LENGTH_SHORT).show()
    }

    private fun unknown() {
        Toast.makeText(context, "UNKNOWN!", Toast.LENGTH_SHORT).show()
    }

    companion object : Navigationable {
        override val TARGET: Int
            get() = R.id.todoCreateFragment
    }

}
