package com.example.lesson_03_yermakov.data.requestmodel

import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)