package com.example.lesson_03_yermakov.domain.usecases

import com.example.lesson_03_yermakov.data.repository.ProductRepository
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend fun execute(id: String): ResponseProduct {
        return repository.getProductByID(id)
    }
}