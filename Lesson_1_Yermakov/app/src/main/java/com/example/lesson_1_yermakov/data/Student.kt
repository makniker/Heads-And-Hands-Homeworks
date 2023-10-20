package com.example.lesson_1_yermakov.data

data class Student(
    private val name: String,
    private val surname: String,
    private val grade: String,
    private val birthdayYear: Int
) {
    override fun toString(): String {
        val result = StringBuilder()
        result.append("$name ")
        result.append("$surname ")
        result.append("$grade ")
        result.append("$birthdayYear ")
        result.append("\n")
        return result.toString()
    }
}
