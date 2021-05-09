package com.carnava.android.auth.domain.usecases

import com.carnava.android.App

class IsSignInUseCase {
    operator fun invoke(): Boolean = App.authRepository.isSignIn()
}