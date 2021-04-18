package com.carnava.android.core.navigation.global

interface OnBackPressedProvider {
    fun canGoBack(): Boolean
    fun onBackPressed()
}