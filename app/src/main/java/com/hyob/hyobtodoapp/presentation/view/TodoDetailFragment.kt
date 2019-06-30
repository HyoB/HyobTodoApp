package com.hyob.hyobtodoapp.presentation.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.hyob.hyobtodoapp.R
import com.hyob.hyobtodoapp.base.Navigationable

class TodoDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todo_detail, container, false)
    }

    companion object: Navigationable {
        override val TARGET: Int
            get() = R.id.todoDetailFragment
    }
}
