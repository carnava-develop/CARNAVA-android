package com.carnava.android.app

import android.app.Application
import com.carnava.android.core.navigation.AppNavigationFactory
import me.aartikov.alligator.AndroidNavigator

class App : Application() {
    companion object {
        val navigator = AndroidNavigator(AppNavigationFactory())
    }
}