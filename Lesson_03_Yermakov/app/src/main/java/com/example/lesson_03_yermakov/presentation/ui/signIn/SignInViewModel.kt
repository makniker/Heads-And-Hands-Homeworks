package com.example.lesson_03_yermakov.presentation.ui.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson_03_yermakov.data.responsemodel.user.ResponseLogin
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _loginLiveData = MutableLiveData<ResponseStates<ResponseLogin>>()
    val loginLiveData: LiveData<ResponseStates<ResponseLogin>> = _loginLiveData

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginLiveData.value = ResponseStates.Loading()
            try {
                _loginLiveData.value = ResponseStates.Success(
                    loginUseCase.execute(email, password)
                )
            } catch (e: Exception) {
                _loginLiveData.value = ResponseStates.Failure(e)
            }
        }
    }
}