package com.example.lesson_03_yermakov.data.repository

import com.example.lesson_03_yermakov.data.NetworkService
import com.example.lesson_03_yermakov.data.requestmodel.RequestLogin
import com.example.lesson_03_yermakov.data.responsemodel.user.ResponseLogin
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val networkService: NetworkService
) {
    suspend fun login(email: String, password: String) : ResponseLogin {
        return networkService.login(RequestLogin(email, password)).data
    }
}