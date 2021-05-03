package com.carnava.android.auth.domain.usecases

import com.carnava.android.App
import com.carnava.android.user.domain.models.UserModel

class SignUpUseCase {
    suspend operator fun invoke(user: UserModel): Boolean {
        val users = App.userRepository.loadAllUsers()
        if (users.find { it.email == user.email } != null) return false
        App.userRepository.saveUser(user)
        App.authRepository.saveEmail(user.email)
        App.authRepository.savePassword(user.password)
        return true
    }
}