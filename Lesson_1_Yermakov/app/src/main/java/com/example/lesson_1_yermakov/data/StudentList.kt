package com.example.lesson_1_yermakov.data

import com.example.lesson_1_yermakov.StudentMapper

class StudentList : Repository {
    private var map = mutableMapOf<Long, Student>()
    private val mapper = StudentMapper()

    override fun saveStudent(text: String) {
        val student = mapper.map(text)
        map[System.currentTimeMillis()] = student
    }

    override fun getListOfStudents(): String {
        val result = StringBuilder()
        for ((id, student) in map) {
            result.append("ID: $id")
            result.append(student.toString())
        }
        return result.toString()
    }
}