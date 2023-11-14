package com.example.lesson_03_yermakov.data.responsemodel.product

import com.google.gson.annotations.SerializedName

data class ResponseProduct(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("department") var department: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("badge") var badge: ResponseBadge? = ResponseBadge(),
    @SerializedName("preview") var preview: String? = null,
    @SerializedName("images") var images: ArrayList<String> = arrayListOf(),
    @SerializedName("sizes") var sizes: ArrayList<ResponseSize> = arrayListOf(),
    @SerializedName("description") var description: String? = null,
    @SerializedName("details") var details: ArrayList<String> = arrayListOf()
)