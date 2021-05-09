package com.carnava.android.user.domain.usecases

import com.carnava.android.App

class LoadUserUseCase {
    suspend operator fun invoke(email: String) = App.userRepository.loadUser(email)
}