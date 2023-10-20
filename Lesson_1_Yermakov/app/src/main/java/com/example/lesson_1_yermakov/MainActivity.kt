package com.example.lesson_1_yermakov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_1_yermakov.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            buttonToFirstScreen.setOnClickListener {
                startActivity(
                    StudentsJournalActivity.openJournalActivity(this@MainActivity)
                )
            }
            buttonToSecondScreen.setOnClickListener {
                startActivity(
                    StudentsListActivity.openListActivity(this@MainActivity)
                )
            }
        }
    }
}