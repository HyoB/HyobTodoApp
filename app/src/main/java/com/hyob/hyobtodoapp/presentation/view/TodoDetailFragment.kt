package com.hyob.hyobtodoapp.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.Navigationable
import com.hyob.hyobtodoapp.base.setNavigationIcon
import com.hyob.hyobtodoapp.base.setSupportActionBar
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoCreateViewModel
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoDetailViewModel
import kotlinx.android.synthetic.main.fragment_todo_detail.*
import kotlinx.android.synthetic.main.toolbar_todo_home.*

class TodoDetailFragment : Fragment() {

    private val args: TodoDetailFragmentArgs by navArgs()

    private val viewModel by lazy {
        ViewModelProviders
            .of(this, TodoDetailViewModel.Factory(requireContext(), args.todoId))
            .get(TodoDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSupportActionBar(toolbar)
        setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

        with(viewModel) {
            updateTodoView()
                .observe(this@TodoDetailFragment, Observer(::updateView))
        }
    }

    private fun updateView(todo: Todo) = with(todo) {
        checkTodoComplete.isChecked = state.isComplete
        textTodoTitle.text = title
        textTodoContent.text = content
    }

    companion object : Navigationable {
        override val TARGET: Int
            get() = R.id.todoDetailFragment
    }
}
