package com.example.lesson_03_yermakov.presentation.ui.signIn

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.core.InputLayoutTextWatcher
import com.example.lesson_03_yermakov.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            loading.visibility = View.INVISIBLE
            buttonSignIn.setOnClickListener {
                loading.visibility = View.VISIBLE
                buttonSignIn.text = ""
                // Имитирую ожидание ответа от сервера.
                // По гайдам отображение прогрессбара делают с использованием стейтов,
                // потом перепишу правильно, если потребуется.
                showSnackbarError(view)
                Handler(Looper.getMainLooper()).postDelayed({
                    navigateToCatalog()
                }, 5000)
            }

            textLogin.addTextChangedListener(
                InputLayoutTextWatcher(
                    layoutLogin,
                    android.util.Patterns.EMAIL_ADDRESS,
                    getString(R.string.input_base_error)
                )
            )

            val passwordREGEX = Pattern.compile(
                "^" +
                        "(?=.*[0-9])" +         //at least 1 digit
                        "(?=.*[a-z])" +         //at least 1 lower case letter
                        "(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        "(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=\\S+$)" +           //no white spaces
                        ".{8,}" +               //at least 8 characters
                        "$"
            )
            textPassword.addTextChangedListener(
                InputLayoutTextWatcher(
                    layoutPassword,
                    passwordREGEX,
                    getString(R.string.input_base_error)
                )
            )

            textPassword.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE
                    || event.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    loading.visibility = View.VISIBLE
                    buttonSignIn.text = ""
                    // Имитирую ожидание ответа от сервера.
                    // По гайдам отображение прогрессбара делают с использованием стейтов,
                    // потом перепишу правильно, если потребуется.
                    showSnackbarError(view)
                    Handler(Looper.getMainLooper()).postDelayed({
                        navigateToCatalog()
                    }, 5000)
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun navigateToCatalog() {
        binding.loading.visibility = View.INVISIBLE
        binding.buttonSignIn.setText(R.string.sign_in_action)
        findNavController(binding.root)
            .navigate(R.id.action_sign_in_fragment_to_catalog_fragment)
    }

    private fun showSnackbarError(view: View) {
        Snackbar.make(view, getString(R.string.snackbar_base_error), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}