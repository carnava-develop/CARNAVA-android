package com.carnava.android.auth.presentation.sign_up

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.auth.domain.usecases.SignUpUseCase
import com.carnava.android.core.navigation.NavigationHelper
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.ui.BaseDialog
import com.carnava.android.databinding.DialogSignUpBinding
import com.carnava.android.user.domain.models.UserModel
import kotlinx.coroutines.launch

class SignUpDialog : BaseDialog(R.layout.dialog_sign_up) {

    private val screenArg: Screens.SignUp by lazy { App.screenResolver.getScreen(this) }
    private val emailArg by lazy { screenArg.email }
    private val passwordArg by lazy { screenArg.password }

    private lateinit var binding: DialogSignUpBinding

    override fun setupView(view: View) {
        binding = DialogSignUpBinding.bind(view)
        with(binding) {
            nameSignUpField.doAfterTextChanged { nameSignUpField.error = null }
            signUpButton.setOnClickListener {
                if (nameSignUpField.text.toString().isNotBlank()) {
                    lifecycleScope.launch {
                        try {
                            SignUpUseCase().invoke(
                                UserModel(
                                    email = emailArg,
                                    password = passwordArg,
                                    name = nameSignUpField.text.toString()
                                )
                            )
                            NavigationHelper.resetMainTabController()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}