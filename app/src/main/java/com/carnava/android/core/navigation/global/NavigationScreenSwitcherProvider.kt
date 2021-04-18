package com.carnava.android.core.navigation.global

import me.aartikov.alligator.Screen
import me.aartikov.alligator.screenswitchers.ScreenSwitcher

interface NavigationScreenSwitcherProvider {
    fun getScreenSwitcher(): ScreenSwitcher
    fun onSwitchScreen(screenFrom: Screen?, screenTo: Screen) {}
}