package com.example.lesson_1_yermakov.data

interface Repository {
    fun saveStudent(text: String)
    fun getListOfStudents(): String
}