package com.carnava.android.user.data.repositories

import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.domain.mappers.toUserEntity
import com.carnava.android.user.domain.mappers.toUserModel
import com.carnava.android.user.domain.mappers.toUsersModels
import com.carnava.android.user.domain.models.UserModel
import com.carnava.android.user.domain.repositories.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun loadAllUsers() = userDao.loadAllUsers().toUsersModels()
    override suspend fun loadUser(email: String) = userDao.loadUser(email).toUserModel()
    override suspend fun saveUser(user: UserModel) = userDao.saveUser(user.toUserEntity())
}