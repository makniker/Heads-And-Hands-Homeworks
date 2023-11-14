package com.example.lesson_03_yermakov.data.responsemodel.product

import com.google.gson.annotations.SerializedName

data class ResponseBadge(
    @SerializedName("value") var value: String? = null,
    @SerializedName("color") var color: String? = null
)