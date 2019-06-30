package com.hyob.hyobtodoapp.base

interface EntityMapper<E, T, VM> {

    fun T.toEntity(): E

    fun E.toViewModel(): VM

    fun E.toDao(): T

}