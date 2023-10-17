package com.example.lesson_1_yermakov

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_1_yermakov.databinding.ActivityMainBinding

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

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