package com.carnava.android.auth.data.repository

import com.carnava.android.auth.data.local.AuthPrefs
import com.carnava.android.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authPrefs: AuthPrefs) : AuthRepository {
    override fun getEmail() = authPrefs.email
    override fun saveEmail(email: String) = kotlin.run { authPrefs.email = email }

    override fun getPassword() = kotlin.run { authPrefs.password }
    override fun savePassword(password: String) = kotlin.run { authPrefs.password = password }

    override fun isSignIn() = authPrefs.isSignIn
}