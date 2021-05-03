package com.carnava.android.user.domain.usecases

import com.carnava.android.App

class LoadUserFromDataBaseUseCase {
    suspend operator fun invoke(email: String) = App.userRepository.loadUser(email)
}