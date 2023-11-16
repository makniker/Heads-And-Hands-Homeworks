package com.example.lesson_03_yermakov.data.responsemodel.user

import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("profile") val profile: ResponseProfile,
)