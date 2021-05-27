package com.carnava.android.app.domain.utils

import com.chibatching.kotpref.KotprefModel

object DatabasePrefs : KotprefModel() {
    val isInitDatabase by booleanPref(default = false, key = "init_database")
}