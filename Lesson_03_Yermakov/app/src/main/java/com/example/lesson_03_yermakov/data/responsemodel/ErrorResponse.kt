package com.example.lesson_03_yermakov.data.responsemodel

import com.google.gson.annotations.SerializedName

data class ErrorBaseResponse(
    @SerializedName("error") val error: ErrorErrorResponse,
)

class ErrorErrorResponse(
    @SerializedName("message") val message: String,
)