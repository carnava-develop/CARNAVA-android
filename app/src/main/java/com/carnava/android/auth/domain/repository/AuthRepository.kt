package com.carnava.android.auth.domain.repository

interface AuthRepository {
    fun getEmail(): String
    fun saveEmail(email: String): Any
    fun getPassword(): String
    fun savePassword(password: String)
    fun isSignIn(): Boolean
}