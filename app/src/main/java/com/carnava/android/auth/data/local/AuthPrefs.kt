package com.carnava.android.auth.data.local

import com.chibatching.kotpref.KotprefModel

class AuthPrefs : KotprefModel() {
    var email: String by stringPref(default = "", key = "email")
    var password: String by stringPref(default = "", key = "password")
    val isSignIn: Boolean get() = email.isNotEmpty() && password.isNotEmpty()
}