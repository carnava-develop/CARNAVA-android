package com.carnava.android.auth.presentation.sign_in

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.auth.domain.usecases.SignInUseCase
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
                lifecycleScope.launch {
                    try {
                        SignInUseCase().invoke(
                            email = emailSignInField.text.toString(),
                            password = passwordSignInField.text.toString()
                        )
//                        App.navigator.reset(Screens.)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            signUpSignInButton.setOnClickListener {
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