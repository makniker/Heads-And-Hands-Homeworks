package com.example.lesson_04_yermakov.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//сделал кастомную фабрику для общего развития, чтобы получать вьюмодель из приложения
// + класть туда гипотетические компоненты в конструктор активити(репозиторий, etc)
class MainViewModelFactory(private val wrapper: LiveDataWrapper) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(wrapper) as T
    }
}