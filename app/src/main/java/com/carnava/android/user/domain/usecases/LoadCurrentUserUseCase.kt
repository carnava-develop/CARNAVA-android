package com.carnava.android.user.domain.usecases

import com.carnava.android.App

class LoadCurrentUserUseCase {
    suspend operator fun invoke() = App.userRepository.loadUser(App.authRepository.getEmail())
}