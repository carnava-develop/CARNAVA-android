package com.carnava.android.auth.domain.usecases

import com.carnava.android.App

class LogoutUseCase {
    operator fun invoke() {
        App.authRepository.saveEmail("")
        App.authRepository.savePassword("")
    }
}