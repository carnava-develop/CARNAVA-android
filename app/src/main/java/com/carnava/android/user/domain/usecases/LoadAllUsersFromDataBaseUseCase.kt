package com.carnava.android.user.domain.usecases

import com.carnava.android.App

class LoadAllUsersFromDataBaseUseCase {
    suspend operator fun invoke() = App.userRepository.loadAllUsers()
}