package com.hyob.hyobtodoapp.presentation.holder

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.BaseViewHolder
import com.hyob.hyobtodoapp.base.RecyclerHolder
import com.hyob.hyobtodoapp.entity.Todo
import com.hyob.hyobtodoapp.presentation.view.TodoDetailFragment
import com.hyob.hyobtodoapp.presentation.view.TodoHomeFragmentDirections
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoHolderViewModel
import kotlinx.android.synthetic.main.holder_todo.view.*

class TodoHolder(itemView: View) : BaseViewHolder<TodoHolderViewModel>(itemView) {

    private val todoTitleTextView = itemView.textTodoTitle
    private val todoCompleteCheckBox = itemView.checkTodoComplete

    override fun bind(vm: TodoHolderViewModel) {
        super.bind(vm)
        todoCompleteCheckBox.apply {
            setOnClickListener {
                vm.updateTodo(isChecked)
            }
        }
        vm.updateTodoView().observe(this@TodoHolder, Observer(::initializeTodoView))
    }

    private fun initializeTodoView(todo: Todo) = with(todo) {
        todoTitleTextView.text = title
        todoCompleteCheckBox.isChecked = state.isComplete
        itemView.setOnClickListener { view ->
            TodoHomeFragmentDirections.actionTodoDetailFragment(todo.id).let { action ->
                view.findNavController().navigate(action)
            }
        }
    }

    companion object {

        fun create(parent: ViewGroup): TodoHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_todo, parent, false)
            return TodoHolder(view)
        }

    }

}