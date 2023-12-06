package com.example.lesson_03_yermakov.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CatalogDAO {
    @Query("SELECT * FROM productcache")
    fun getAll(): List<ProductCache>

    @Query("SELECT * FROM productcache WHERE id= :id LIMIT 1")
    fun getByID(id: String): ProductCache

    @Query("SELECT COUNT(*) AS count FROM productcache WHERE id = :id")
    fun checkID(id: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<ProductCache>)

    @Delete
    fun delete(product: ProductCache)
}