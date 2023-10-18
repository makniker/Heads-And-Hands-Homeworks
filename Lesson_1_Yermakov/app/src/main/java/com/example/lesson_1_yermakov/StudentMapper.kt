package com.example.lesson_1_yermakov

import com.example.lesson_1_yermakov.data.Student

object StudentMapper {
    fun map(text: String): Student {
        val parts = text.split(" ")
        var year: Int
        var grade: String
        try {
            year = parts[3].toInt()
            grade = parts[2]
            if (parts.size != 4) {
                throw Exception("Not so much arguments")
            }
            if (parts[0].isEmpty() || parts[1].isEmpty()) {
                throw Exception("Empty string")
            }
            if (!grade[0].isDigit()
                || grade[1].uppercaseChar() !in 'А'..'Е'
                || grade.length != 2) {
                throw Exception("Bad grade input!")
            }
            if (year !in 1..2022) {
                throw Exception("Bad grade input!")
            }
            return Student(parts[0], parts[1], parts[2], parts[3].toInt())
        } catch (e: NumberFormatException) {
            throw Exception("Cant convert year to string")
        } catch (e: IllegalArgumentException) {
            throw Exception("Cant split string")
        }
    }
}