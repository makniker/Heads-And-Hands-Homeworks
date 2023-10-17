package com.example.lesson_1_yermakov

import com.example.lesson_1_yermakov.data.Student
import java.io.IOException

class StudentMapper {
    private fun isGoodString(parts: List<String>): Boolean {
        val year: Int = parts[3].toInt()
        val grade: String = parts[2]
        return parts.size == 4
                && parts[0].isNotEmpty()
                && parts[1].isNotEmpty()
                && (year in 1..2022)
                && grade.length == 2
                && grade[0].isDigit()
                && (grade[1].uppercaseChar() in 'А' .. 'Е')
    }

    fun map(text: String): Student {
        val parts = text.split(" ")
        if (isGoodString(parts)) {
            return Student(parts[0], parts[1], parts[2], parts[3].toInt())
        }
        throw IOException("Wrong String!")
    }
}