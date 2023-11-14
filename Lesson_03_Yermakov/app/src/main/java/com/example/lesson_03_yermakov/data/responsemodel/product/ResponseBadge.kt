package com.example.lesson_03_yermakov.data.responsemodel.product

import com.google.gson.annotations.SerializedName

data class ResponseBadge(
    @SerializedName("value") val value: String,
    @SerializedName("color") val color: String
)