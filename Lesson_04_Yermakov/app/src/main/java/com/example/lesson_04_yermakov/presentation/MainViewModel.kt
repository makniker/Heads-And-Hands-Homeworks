package com.example.lesson_04_yermakov.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel(private val wrapper: LiveDataWrapper) : ViewModel() {
    fun save(text: String) {
        wrapper.save(text)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        wrapper.observe(owner, observer)
}