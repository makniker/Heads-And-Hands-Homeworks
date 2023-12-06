package com.example.lesson_03_yermakov.data.responsemodel.product

import com.example.lesson_03_yermakov.core.Mapper
import com.example.lesson_03_yermakov.data.cache.ProductCache
import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("department") val department: String,
    @SerializedName("price") val price: Int,
    @SerializedName("badge") val badge: ArrayList<ResponseBadge>,
    @SerializedName("preview") val preview: String,
    @SerializedName("images") val images: ArrayList<String>,
    @SerializedName("sizes") val sizes: ArrayList<ResponseSize>,
    @SerializedName("description") val description: String,
    @SerializedName("details") val details: ArrayList<String>
) : Mapper<ProductCache> {
    override fun to(): ProductCache =
        ProductCache(
            id,
            title,
            department,
            price,
            badge,
            preview,
            images,
            sizes,
            description,
            details)
}