package com.example.lesson_03_yermakov.data.cache.converter

import androidx.room.TypeConverter
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseBadge
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResponseBadgeConverter {
    @TypeConverter
    fun fromResponseBadge(badges: ArrayList<ResponseBadge>): String {
        return Gson().toJson(badges)
    }

    @TypeConverter
    fun toResponseBadge(badges: String): ArrayList<ResponseBadge> {
        return Gson().fromJson(badges, object : TypeToken<ArrayList<ResponseBadge>>() {}.type)
    }
}