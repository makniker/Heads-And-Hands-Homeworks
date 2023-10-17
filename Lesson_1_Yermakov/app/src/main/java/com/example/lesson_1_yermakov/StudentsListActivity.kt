package com.example.lesson_1_yermakov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_1_yermakov.data.Repository
import com.example.lesson_1_yermakov.data.StudentList
import com.example.lesson_1_yermakov.databinding.ActivityStudentsListBinding

class StudentsListActivity : AppCompatActivity() {

    companion object {
        fun openListActivity(context: Context) = Intent(context, StudentsListActivity::class.java)
    }

    private lateinit var binding: ActivityStudentsListBinding
    private val studentList: Repository = StudentList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            // выводим в текствью содержимое листа
            showStudentsListButton.setOnClickListener {
                studentsListTextView.text = studentList.getListOfStudents()
                studentsListEditText.text.clear()
                hideKeyboard()
            }

            // сохраняем студента в наше хранилище, если не удается распарсить строку
            // показываем сообщение об ошибке.
            studentsListEditText.setOnClickListener {
                studentsListEditText.setOnKeyListener { _, keyCode, keyEvent ->
                    if ((keyEvent.action == KeyEvent.ACTION_UP) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)
                    ) {
                        studentsListEditText.text.clear()
                        try {
                            val text = studentsListEditText.text.toString()
                            studentList.saveStudent(text)
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@StudentsListActivity,
                                getText(R.string.input_error),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        return@setOnKeyListener true
                    }
                    false
                }
            }
        }
    }
}
