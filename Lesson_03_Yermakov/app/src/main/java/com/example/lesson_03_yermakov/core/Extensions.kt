package com.example.lesson_03_yermakov.core

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.lesson_03_yermakov.data.responsemodel.ErrorBaseResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = requireActivity().currentFocus
    if (view != null) {
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Exception.getError(): String? {
    return if (this is HttpException) {
        val errorBody = response()?.errorBody()?.string()
        Gson().fromJson(errorBody, ErrorBaseResponse::class.java).error.message
    } else {
        message
    }
}