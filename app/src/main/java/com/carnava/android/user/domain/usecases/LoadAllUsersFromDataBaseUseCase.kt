package com.carnava.android.user.domain.usecases

import com.carnava.android.App
import com.carnava.android.user.domain.mappers.toUsersModels

class LoadAllUsersFromDataBaseUseCase {
    suspend operator fun invoke() = App.userDao.loadAllUsers().toUsersModels()
}