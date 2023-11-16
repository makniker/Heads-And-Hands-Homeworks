package com.example.lesson_03_yermakov.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.core.fitContentViewToInsets
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fitContentViewToInsets(false)
    }
}