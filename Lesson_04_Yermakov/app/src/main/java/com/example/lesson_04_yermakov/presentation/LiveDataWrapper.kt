package com.example.lesson_04_yermakov.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

//обертка, в которой просто лежит лайвдата, чтобы было что класть в конструктор
class LiveDataWrapper {
    private val storedLiveData by lazy { MutableLiveData<String>() }
    fun save(text: String) {
        storedLiveData.value = text
    }

    fun observe(owner: LifecycleOwner, observer: Observer<String>) =
        storedLiveData.observe(owner, observer)
}