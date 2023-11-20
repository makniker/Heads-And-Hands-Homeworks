package com.example.lesson_03_yermakov.data.datasource

import com.example.lesson_03_yermakov.data.cache.CatalogDAO
import com.example.lesson_03_yermakov.data.cache.ProductCache
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class CacheCatalogDataSource @Inject constructor(private val catalogDAO: CatalogDAO) :
    CatalogDataSource {
    override suspend fun getCatalog(): List<ResponseProduct> =
        catalogDAO.getAll().map {
            ResponseProduct(
                it.id,
                it.title,
                it.department,
                it.price,
                it.badge,
                it.preview,
                it.images,
                it.sizes,
                it.description,
                it.details
            )
        }

    fun saveCatalog(list: List<ResponseProduct>) = catalogDAO.insertAll(
        list.map {
            ProductCache(
                it.id,
                it.title,
                it.department,
                it.price,
                it.badge,
                it.preview,
                it.images,
                it.sizes,
                it.description,
                it.details
            )
        }
    )

}