package com.hyob.hyobtodoapp.base

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VM : ViewModel>(itemView: View): RecyclerView.ViewHolder(itemView), LifecycleOwner {

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = registry

    open fun bind(vm: VM) {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    open fun clear() {
        registry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

}