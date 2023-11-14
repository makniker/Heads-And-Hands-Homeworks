package com.example.lesson_03_yermakov.domain.usecases

import com.example.lesson_03_yermakov.data.repository.LoginRepository
import com.example.lesson_03_yermakov.data.repository.PreferenceStorage
import com.example.lesson_03_yermakov.data.responsemodel.user.ResponseLogin
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val preferenceStorage: PreferenceStorage,
) {
    suspend fun execute(email: String, password: String): ResponseLogin {
        val loginData = repository.login(email, password)
        preferenceStorage.userToken = loginData.accessToken
        return loginData
    }
}