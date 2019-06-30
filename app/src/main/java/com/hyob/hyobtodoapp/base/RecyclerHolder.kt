package com.hyob.hyobtodoapp.base

import android.view.ViewGroup
import java.net.IDN

interface RecyclerHolder<V, T> {

    val ID: Int

    fun create(parent: ViewGroup, delegate: T): V

}