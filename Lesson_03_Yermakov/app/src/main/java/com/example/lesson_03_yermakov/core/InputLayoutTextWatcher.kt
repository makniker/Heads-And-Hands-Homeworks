package com.example.lesson_03_yermakov.core

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

open class InputLayoutTextWatcher(
    private val layout: TextInputLayout,
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
        layout.error = null
    }
}

class InputLayoutTextWatcherWithPattern(
    private val layout: TextInputLayout,
    private val pattern: Pattern,
    private val errorText: String
) : InputLayoutTextWatcher(layout) {
    override fun afterTextChanged(s: Editable?) {
        if (!pattern.matcher(s.toString()).matches()){
            layout.error = errorText
        } else {
            layout.error = null
        }
    }
}