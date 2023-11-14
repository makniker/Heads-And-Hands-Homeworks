package com.example.lesson_03_yermakov.data.responsemodel.product

import com.google.gson.annotations.SerializedName

data class ResponseSize(
    @SerializedName("value") var value: String? = null,
    @SerializedName("isAvailable") var isAvailable: String? = null
)