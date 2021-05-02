package com.carnava.android.user.domain.usecases

import com.carnava.android.App
import com.carnava.android.user.domain.mappers.toUserEntity
import com.carnava.android.user.domain.models.UserModel

class SaveUserIntoDataBaseUseCase {
    suspend operator fun invoke(user: UserModel) = App.userDao.saveUser(user.toUserEntity())
}