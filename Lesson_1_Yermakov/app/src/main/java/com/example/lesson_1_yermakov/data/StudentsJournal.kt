package com.example.lesson_1_yermakov.data

import com.example.lesson_1_yermakov.StudentMapper

class StudentsJournal : Repository {
    private val set = mutableSetOf<Student>()
    override fun saveStudent(text: String) {
        val student = StudentMapper.map(text)
        set.add(student)
    }

    override fun getListOfStudents(): String {
        val result = StringBuilder()
        for (student in set) {
            result.append(student.toString())
        }
        return result.toString()
    }
}