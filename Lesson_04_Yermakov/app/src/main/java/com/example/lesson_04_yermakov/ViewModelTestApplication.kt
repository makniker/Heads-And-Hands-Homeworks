package com.example.lesson_04_yermakov

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.lesson_04_yermakov.presentation.LiveDataWrapper
import com.example.lesson_04_yermakov.presentation.MainViewModelFactory

class ViewModelTestApplication : Application() {
    private val factory by lazy { MainViewModelFactory(LiveDataWrapper()) }
    fun <T : ViewModel> get(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory)[modelClass]
}