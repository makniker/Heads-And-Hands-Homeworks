package com.example.lesson_03_yermakov.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseBadge
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseSize

@Entity
@TypeConverters
data class ProductCache(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "department") val department: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "badge") val badge: ArrayList<ResponseBadge>,
    @ColumnInfo(name = "preview") val preview: String,
    @ColumnInfo(name = "images") val images: ArrayList<String>,
    @ColumnInfo(name = "sizes") val sizes: ArrayList<ResponseSize>,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "details") val details: ArrayList<String>
)