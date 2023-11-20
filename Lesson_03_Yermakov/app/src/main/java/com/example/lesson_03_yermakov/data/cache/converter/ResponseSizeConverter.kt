package com.example.lesson_03_yermakov.data.cache.converter

import androidx.room.TypeConverter
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseSize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResponseSizeConverter {
    @TypeConverter
    fun fromResponseBadge(sizes: ArrayList<ResponseSize>): String {
        return Gson().toJson(sizes)
    }

    @TypeConverter
    fun toResponseBadge(sizes: String): ArrayList<ResponseSize> {
        return Gson().fromJson(sizes, object : TypeToken<ArrayList<ResponseSize>>() {}.type)
    }
}