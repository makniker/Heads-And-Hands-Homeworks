package com.example.lesson_04_yermakov.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lesson_04_yermakov.R
import com.example.lesson_04_yermakov.ViewModelTestApplication
import com.example.lesson_04_yermakov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mainViewModel =
            (application as ViewModelTestApplication).get(MainViewModel::class.java, this)
        with(binding) {
            val observer = Observer<String> { storedText -> storedMessageView.text = storedText }
            saveButton.setOnClickListener {
                val text = inputMessage.editText?.text.toString()
                mainViewModel.save(text)
            }
            mainViewModel.observe(this@MainActivity, observer)
        }
    }
}
