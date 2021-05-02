package com.carnava.android.core.navigation

import com.carnava.android.app.presentation.AppActivity
import com.carnava.android.core.navigation.fragment_controllers.NavigationControllerFragment
import com.carnava.android.core.navigation.fragment_controllers.TabNavigationControllerFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppNavigationFactory : RegistryNavigationFactory() {
    init {
        registerActivity(Screens.Application::class.java, AppActivity::class.java)
        registerFragment(Screens.TabNavigationController::class.java, TabNavigationControllerFragment::class.java)
        registerFragment(Screens.NavigationController::class.java, NavigationControllerFragment::class.java)
    }
}