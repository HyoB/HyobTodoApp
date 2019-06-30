package com.hyob.hyobtodoapp.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment: Fragment() {

    

    abstract fun initViewModel(): ViewModel

}