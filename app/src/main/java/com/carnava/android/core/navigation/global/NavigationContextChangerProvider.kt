package com.carnava.android.core.navigation.global

import androidx.fragment.app.Fragment

interface NavigationContextChangerProvider {
    fun setNavigationContext(fragment: Fragment)
    fun setFirstTabNavigationContext()
    fun setLastTabNavigationContext(lastFragment: Fragment)
    fun defaultNavigationContext()
    fun resetNavigationContext()
}