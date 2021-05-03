package com.carnava.android.auth.domain.usecases

import com.carnava.android.App

class SignInUseCase {
    suspend operator fun invoke(email: String, password: String): Boolean {
        val user = App.userRepository.loadUser(email)
        return if (email == user.email && password == user.password) {
            App.authRepository.saveEmail(user.email)
            App.authRepository.savePassword(user.password)
            true
        } else {
            false
        }
    }
}