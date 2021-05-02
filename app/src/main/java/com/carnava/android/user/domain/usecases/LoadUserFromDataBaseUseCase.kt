package com.carnava.android.user.domain.usecases

import com.carnava.android.App
import com.carnava.android.user.domain.mappers.toUserModel

class LoadUserFromDataBaseUseCase {
    suspend operator fun invoke(email: String) = App.userDao.loadUser(email).toUserModel()
}