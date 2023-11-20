package com.example.lesson_03_yermakov.data.datasource

import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct

interface CatalogDataSource {
    suspend fun getCatalog() : List<ResponseProduct>
}