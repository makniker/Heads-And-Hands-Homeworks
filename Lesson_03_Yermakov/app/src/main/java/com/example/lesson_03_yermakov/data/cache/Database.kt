package com.example.lesson_03_yermakov.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lesson_03_yermakov.data.cache.converter.ResponseBadgeConverter
import com.example.lesson_03_yermakov.data.cache.converter.ResponseSizeConverter
import com.example.lesson_03_yermakov.data.cache.converter.StringArrayConverter


@Database(entities = [ProductCache::class], version = 1)
@TypeConverters(ResponseBadgeConverter::class, StringArrayConverter::class, ResponseSizeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catalogDao(): CatalogDAO
}
