package com.example.lesson_03_yermakov.data.responsemodel.product

import com.google.gson.annotations.SerializedName

data class ResponseSize(
    @SerializedName("value") val value: String,
    @SerializedName("isAvailable") val isAvailable: String
)