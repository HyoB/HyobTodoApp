package com.hyob.hyobtodoapp.base

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<VM : ViewModel>(itemView: View): RecyclerView.ViewHolder(itemView) {

    abstract fun bind(vm: VM)

}