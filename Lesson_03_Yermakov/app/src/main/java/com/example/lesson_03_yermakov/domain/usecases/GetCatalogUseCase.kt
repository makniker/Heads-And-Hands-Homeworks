package com.example.lesson_03_yermakov.domain.usecases

import com.example.lesson_03_yermakov.data.repository.ProductRepository
import com.example.lesson_03_yermakov.presentation.ui.catalog.UIModelCatalogProduct
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    suspend fun execute(): MutableList<UIModelCatalogProduct> {
        return repository.getCatalog().map {
            UIModelCatalogProduct(
                it.id,
                it.title,
                it.department,
                it.price,
                it.preview
            )
        } as MutableList<UIModelCatalogProduct>
    }
}