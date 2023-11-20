package com.example.lesson_03_yermakov.data.cache.converter

import androidx.room.TypeConverter

class StringArrayConverter {
    @TypeConverter
    fun fromStringArray(value: ArrayList<String>): String {
        return value.joinToString(separator = ";")
    }

    @TypeConverter
    fun toStringArray(value: String): ArrayList<String> {
        return ArrayList(value.split(";"))
    }
}