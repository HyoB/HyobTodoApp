package com.hyob.hyobtodoapp.presentation.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hyob.hyobtodoapp.presentation.holder.TodoHolder
import com.hyob.hyobtodoapp.presentation.viewmodel.TodoHolderViewModel

class TodoListAdapter: ListAdapter<TodoHolderViewModel, TodoHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder =
        TodoHolder.create(parent)

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(super.getItem(position))
    }

    override fun onViewRecycled(holder: TodoHolder) {
        super.onViewRecycled(holder)
        holder.clear()
        Log.d("TodoHolder", "onViewRecycled")
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<TodoHolderViewModel>() {
            override fun areItemsTheSame(oldItem: TodoHolderViewModel, newItem: TodoHolderViewModel): Boolean =
                oldItem.getIdentifier() == newItem.getIdentifier()

            override fun areContentsTheSame(oldItem: TodoHolderViewModel, newItem: TodoHolderViewModel): Boolean =
                false
        }
    }

}