package com.hyob.hyobtodoapp.presentation.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.RecyclerHolder
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.presentation.view.TodoDetailFragment
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoHolderViewModel
import kotlinx.android.synthetic.main.holder_todo.view.*

class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val todoTitleTextView = itemView.textTodoTitle
    private val todoCompleteCheckBox = itemView.checkTodoComplete

    fun bind(viewModel: TodoHolderViewModel) = with(viewModel) {
        initView {
            todoTitleTextView.text = it.title
            todoCompleteCheckBox.isChecked = it.state.isComplete
        }

        todoCompleteCheckBox.apply {
            setOnClickListener {
                updateTodoState(isChecked) {
                    updateTodoView(it)
                }
            }
        }

        itemView.setOnClickListener { view ->
            view.findNavController().navigate(TodoDetailFragment.TARGET)
        }
    }

    private fun updateTodoView(todo: Todo) = with(todo) {
        Log.d("updateTodoView", "$todo")
        todoTitleTextView.text = title
        todoCompleteCheckBox.isChecked = state.isComplete
    }

    companion object {

        fun create(parent: ViewGroup): TodoHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_todo, parent, false)
            return TodoHolder(view)
        }

    }

}