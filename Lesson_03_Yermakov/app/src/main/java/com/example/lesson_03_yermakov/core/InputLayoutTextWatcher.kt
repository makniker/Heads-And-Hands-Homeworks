package com.example.lesson_03_yermakov.core

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

open class InputLayoutTextWatcher(
    private val layout: TextInputLayout,
    private val pattern: Pattern,
    private val errorText: String
) : TextWatcher {
    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        layout.isErrorEnabled =
            !pattern.matcher(s.toString()).matches()
        if (layout.isErrorEnabled) {
            layout.error = errorText
        }
    }
}