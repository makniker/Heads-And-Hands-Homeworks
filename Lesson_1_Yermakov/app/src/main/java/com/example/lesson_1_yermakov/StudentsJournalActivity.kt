package com.example.lesson_1_yermakov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_1_yermakov.data.Repository
import com.example.lesson_1_yermakov.data.StudentsJournal
import com.example.lesson_1_yermakov.databinding.ActivityStudentsJournalBinding

class StudentsJournalActivity : AppCompatActivity() {

    companion object {
        fun openJournalActivity(context: Context) =
            Intent(context, StudentsJournalActivity::class.java)
    }

    private lateinit var binding: ActivityStudentsJournalBinding
    private val studentsJournal: Repository = StudentsJournal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {

            // сохраняем студента в наше хранилище, если не удается распарсить строку
            // показываем сообщение об ошибке.
            saveStudentButton.setOnClickListener {
                try {
                    val text = studentsJournalEditText.text.toString()
                    studentsJournal.saveStudent(text)
                } catch (e: Exception) {
                    Toast.makeText(
                        this@StudentsJournalActivity,
                        getText(R.string.input_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
                studentsJournalEditText.text.clear()
            }

            // выводим в текствью содержимое хранилища
            showStudentsButton.setOnClickListener {
                studentsTextView.text = studentsJournal.getListOfStudents()
                studentsJournalEditText.text.clear()
                hideKeyboard()
            }
        }
    }
}
