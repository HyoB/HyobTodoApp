package com.hyob.hyobtodoapp.base

interface EntityMapper<E, T, VM> {

    fun toDao(entity: E): T

    fun toEntity(dao: T): E

    fun toViewModel(entity: E): VM

}