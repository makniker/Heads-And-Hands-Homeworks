package com.example.lesson_03_yermakov.presentation.ui.signIn

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.lesson_03_yermakov.R
import com.example.lesson_03_yermakov.core.InputLayoutTextWatcher
import com.example.lesson_03_yermakov.core.InputLayoutTextWatcherWithPattern
import com.example.lesson_03_yermakov.core.getError
import com.example.lesson_03_yermakov.core.hideKeyboard
import com.example.lesson_03_yermakov.data.responsemodel.ResponseStates
import com.example.lesson_03_yermakov.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SignInFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by createViewModelLazy(
        SignInViewModel::class,
        { this.viewModelStore },
        factoryProducer = { viewModelFactory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

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

            buttonSignIn.setStateData()

            viewModel.loginLiveData.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is ResponseStates.Success -> {
                        buttonSignIn.setStateData()
                        navigateToCatalog()
                    }

                    is ResponseStates.Failure -> {
                        buttonSignIn.setStateData()
                        result.e.getError()?.let { showSnackbarError(view, it) }
                            ?: showSnackbarError(view)
                    }

                    is ResponseStates.Loading -> {
                        buttonSignIn.setStateLoading()
                    }
                }
            }

            buttonSignIn.setOnClickListener {
                performLogin()
            }

            textLogin.addTextChangedListener(
                InputLayoutTextWatcherWithPattern(
                    layoutLogin,
                    android.util.Patterns.EMAIL_ADDRESS,
                    getString(R.string.input_base_error)
                )
            )

            textPassword.addTextChangedListener(
                InputLayoutTextWatcher(
                    layoutPassword,
                )
            )

            textPassword.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE
                    || event.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    performLogin()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun isGoodInput(): Boolean {
        with(binding) {
            if (TextUtils.isEmpty(textLogin.text)) {
                layoutLogin.error = getString(R.string.input_empty_error)
            }
            if (TextUtils.isEmpty(textPassword.text)) {
                layoutPassword.error = getString(R.string.input_empty_error)
            }
            return TextUtils.isEmpty(textLogin.error)
                    && TextUtils.isEmpty(textPassword.error)
                    && !TextUtils.isEmpty(textLogin.text)
                    && !TextUtils.isEmpty(textPassword.text)
        }
    }

    private fun performLogin() {
        if (isGoodInput()) {
            hideKeyboard()
            viewModel.login(binding.textLogin.text.toString(), binding.textPassword.text.toString())
        }
    }

    private fun navigateToCatalog() {
        findNavController(binding.root).navigate(SignInFragmentDirections.actionSignInFragmentToCatalogFragment())
    }

    private fun showSnackbarError(view: View, error: String) {
        Snackbar.make(view, error, Snackbar.LENGTH_LONG)
            .show()
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