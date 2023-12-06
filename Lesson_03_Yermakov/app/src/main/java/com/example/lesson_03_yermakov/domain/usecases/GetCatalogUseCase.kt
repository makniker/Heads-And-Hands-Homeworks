package com.example.lesson_03_yermakov.domain.usecases

import android.content.Context
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.data.repository.ProductRepository
import com.example.lesson_03_yermakov.presentation.ui.catalog.UIModelCatalogProduct
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val repository: ProductRepository,
) {
    @Inject
    lateinit var context: Context
    suspend fun execute(): MutableList<UIModelCatalogProduct> {
        return repository.getCatalog().map {
            UIModelCatalogProduct(
                it.id,
                it.title,
                it.department,
                context.getString(R.string.price_format, it.price.toString()),
                it.preview
            )
        } as MutableList<UIModelCatalogProduct>
    }
}