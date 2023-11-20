package com.example.lesson_03_yermakov.data.datasource

import com.example.lesson_03_yermakov.data.NetworkService
import com.example.lesson_03_yermakov.data.responsemodel.product.ResponseProduct
import javax.inject.Inject

class CloudCatalogDataSource@Inject constructor(
    private val networkService: NetworkService
): CatalogDataSource {

    override suspend fun getCatalog(): List<ResponseProduct> {
        return networkService.getCatalog().data
    }
}