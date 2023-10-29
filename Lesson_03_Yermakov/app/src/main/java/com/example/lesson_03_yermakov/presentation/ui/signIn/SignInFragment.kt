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
import androidx.navigation.Navigation
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar

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

            textPassword.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    loading.visibility = View.VISIBLE
                    buttonSignIn.text = ""
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
        Navigation.findNavController(binding.root)
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