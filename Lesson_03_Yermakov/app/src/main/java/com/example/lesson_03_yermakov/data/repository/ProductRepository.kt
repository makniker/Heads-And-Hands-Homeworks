package com.example.lesson_03_yermakov.data.repository

import com.example.lesson_03_yermakov.data.NetworkService
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun getCatalog(): List<ResponseProduct> {
        return networkService.getCatalog().data
    }
}