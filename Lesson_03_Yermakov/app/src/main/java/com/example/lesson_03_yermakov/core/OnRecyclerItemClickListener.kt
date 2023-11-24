package com.example.lesson_03_yermakov.core

interface OnRecyclerItemClickListener<T> {
    fun onClick(item: T)
}