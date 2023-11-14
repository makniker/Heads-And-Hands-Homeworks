package com.example.lesson_03_yermakov.di

import android.content.Context
import com.example.lesson_03_yermakov.MyApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: MyApplication): Context {
        return app.applicationContext
    }
}