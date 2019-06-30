package com.hyob.hyobtodoapp.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

fun Fragment.setSupportActionBar(toolbar: androidx.appcompat.widget.Toolbar?) {
    (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
    setHasOptionsMenu(true)
}

fun Fragment.setNavigationIcon(res: Int) {
    (activity as? AppCompatActivity)?.supportActionBar?.run {
        setDisplayHomeAsUpEnabled(true)
        setHomeAsUpIndicator(res)
    }
}

fun Fragment.hideKeyboard() {
    val view = view
    if (view != null) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun View.gone() {
    if(visibility != View.GONE)
        this.visibility = View.GONE
}

fun View.visible() {
    if(visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun EditText.text() =
    this.text.toString()