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
                    if ((keyEvent.action == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)
                    ) {
                        try {
                            val text = studentsListEditText.text.toString()
                            studentList.saveStudent(text)
                            Toast.makeText(
                                this@StudentsListActivity,
                                getText(R.string.input_success),
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                this@StudentsListActivity,
                                e.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        studentsListEditText.text.clear()
                        return@setOnKeyListener true
                    }
                    false
                }
            }
        }
    }
}
