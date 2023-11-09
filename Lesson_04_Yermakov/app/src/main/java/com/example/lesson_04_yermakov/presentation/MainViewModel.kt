package com.example.lesson_04_yermakov.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val storedLiveData by lazy { MutableLiveData<String>() }
    fun save(text: String) {
        storedLiveData.value = text
    }
}