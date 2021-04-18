package com.carnava.android.core.extensions

import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && EMAIL_ADDRESS.matcher(this).matches()
}
