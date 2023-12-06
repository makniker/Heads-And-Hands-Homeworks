package com.example.lesson_03_yermakov.data.repository

import com.example.lesson_03_yermakov.data.datasource.CacheCatalogDataSource
import com.example.lesson_03_yermakov.data.datasource.CloudCatalogDataSource
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val cacheCatalogDataSource: CacheCatalogDataSource,
    private val cloudCatalogDataSource: CloudCatalogDataSource
) {
    suspend fun getCatalog(): List<ResponseProduct> {
        val cache = cacheCatalogDataSource.getCatalog()
        val fetchedData = cloudCatalogDataSource.getCatalog()
        cacheCatalogDataSource.saveCatalog(fetchedData)
        return cache.ifEmpty {
            fetchedData
        }
    }

    suspend fun getProductByID(id: String): ResponseProduct {
        return if (cacheCatalogDataSource.isIDExist(id)) {
            cloudCatalogDataSource.getProductByID(id)
        } else {
            cacheCatalogDataSource.getProductByID(id)
        }
    }
}