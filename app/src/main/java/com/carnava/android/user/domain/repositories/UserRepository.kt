package com.carnava.android.user.domain.repositories

import com.carnava.android.user.domain.models.UserModel

interface UserRepository {
    suspend fun loadAllUsers(): List<UserModel>
    suspend fun loadUser(email: String): UserModel
    suspend fun saveUser(user: UserModel)
}