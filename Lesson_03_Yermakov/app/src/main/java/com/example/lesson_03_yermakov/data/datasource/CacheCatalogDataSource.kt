package com.example.lesson_03_yermakov.data.datasource

import com.example.lesson_03_yermakov.data.cache.CatalogDAO
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class CacheCatalogDataSource @Inject constructor(private val catalogDAO: CatalogDAO) :
    CatalogDataSource {
    override suspend fun getCatalog(): List<ResponseProduct> =
        catalogDAO.getAll().map {
            it.to()
        }

    override suspend fun getProductByID(id: String): ResponseProduct =
        catalogDAO.getByID(id).to()

    fun saveCatalog(list: List<ResponseProduct>) = catalogDAO.insertAll(
        list.map {
            it.to()
        }
    )

}