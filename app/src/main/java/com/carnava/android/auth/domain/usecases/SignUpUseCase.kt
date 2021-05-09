package com.carnava.android.auth.domain.usecases

import com.carnava.android.App
import com.carnava.android.user.domain.models.UserModel

class SignUpUseCase {
    suspend operator fun invoke(user: UserModel) {
        val users = App.userRepository.loadAllUsers()
        if (users.find { it.email == user.email } != null) throw IllegalArgumentException("User already exist")
        App.userRepository.saveUser(user)
        App.authRepository.saveEmail(user.email)
        App.authRepository.savePassword(user.password)
    }
}