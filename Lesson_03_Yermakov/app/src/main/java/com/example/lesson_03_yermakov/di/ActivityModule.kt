package com.example.lesson_03_yermakov.di


import com.example.lesson_03_yermakov.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}