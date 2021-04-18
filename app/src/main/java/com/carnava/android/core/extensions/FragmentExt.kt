package com.carnava.android.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.carnava.android.core.navigation.global.NavigationContextChangerProvider

fun Fragment.requireAppCompatActivity() = requireActivity() as AppCompatActivity

fun Fragment.requireNavigationContextChanger() = requireActivity() as NavigationContextChangerProvider
