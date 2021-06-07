package com.carnava.android.auth.presentation.sign_in

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.auth.domain.usecases.SignInUseCase
import com.carnava.android.core.extensions.isEmailValid
import com.carnava.android.core.navigation.NavigationHelper
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseFragment
import com.carnava.android.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    override fun setupView(view: View) {
        binding = FragmentSignInBinding.bind(view)
        with(binding) {
            emailSignInField.doAfterTextChanged { emailSignInField.error = null }
            passwordSignInField.doAfterTextChanged { passwordSignInField.error = null }
            signInButton.setOnClickListener {
                when {
                    emailSignInField.text.isBlank() -> {
                        emailSignInField.error =
                            getString(R.string.error_fill_in_the_required_field)
                    }
                    !emailSignInField.text.toString().isEmailValid() -> {
                        emailSignInField.error =
                            getString(R.string.error_email_is_not_correct)
                    }
                }

                if (passwordSignInField.text.isBlank()) {
                    passwordSignInField.error =
                        getString(R.string.error_fill_in_the_required_field)
                }

                if (passwordSignInField.text.isBlank()
                    || emailSignInField.text.isBlank()
                    || !emailSignInField.text.toString().isEmailValid()
                ) return@setOnClickListener

                lifecycleScope.launch {
                    try {
                        SignInUseCase().invoke(
                            email = emailSignInField.text.toString(),
                            password = passwordSignInField.text.toString()
                        )
                        NavigationHelper.resetMainTabController()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            signUpSignInButton.setOnClickListener {
                when {
                    emailSignInField.text.isBlank() -> {
                        emailSignInField.error =
                            getString(R.string.error_fill_in_the_required_field)
                    }
                    !emailSignInField.text.toString().isEmailValid() -> {
                        emailSignInField.error =
                            getString(R.string.error_email_is_not_correct)
                    }
                }

                if (passwordSignInField.text.isBlank()) {
                    passwordSignInField.error =
                        getString(R.string.error_fill_in_the_required_field)
                }

                if (passwordSignInField.text.isBlank()
                    || emailSignInField.text.isBlank()
                    || !emailSignInField.text.toString().isEmailValid()
                ) return@setOnClickListener

                if (emailSignInField.text.isNotBlank() && passwordSignInField.text.isNotBlank()) {
                    App.navigator.goForward(
                        Screens.SignUp(
                            email = emailSignInField.text.toString(),
                            password = passwordSignInField.text.toString()
                        )
                    )
                }
            }
        }
    }
}