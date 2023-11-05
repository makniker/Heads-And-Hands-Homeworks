package com.example.lesson_04_yermakov.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val storedLiveData by lazy { MutableLiveData<String>() }
    fun save(text: String) {
        storedLiveData.value = text
    }

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        storedLiveData.observe(owner, observer)
}