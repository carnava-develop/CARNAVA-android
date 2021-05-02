package com.carnava.android.core.navigation.fragment_controllers

import android.os.Bundle
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.core.extensions.requireNavigationContextChanger
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.navigation.controllers.NavigationControllerContract
import com.carnava.android.core.ui.BaseFragment
import me.aartikov.alligator.DestinationType
import me.aartikov.alligator.Screen
import me.aartikov.alligator.TransitionType

class NavigationControllerFragment : BaseFragment(R.layout.fragment_navigation_controller),
    NavigationControllerContract {

    private val navigator = App.navigator

    var countChildScreen = 0
    override fun getContainerId(): Int = R.id.container_navigation_controller
    override fun canGoBack(): Boolean = countChildScreen > 1
    override fun onBackPressed() = navigator.goBack()

    override fun onTransactionScreen(
        transitionType: TransitionType,
        destinationType: DestinationType,
        screenClassFrom: Class<out Screen>?,
        screenClassTo: Class<out Screen>?
    ) {
        countChildScreen = when (transitionType) {
            TransitionType.FORWARD -> countChildScreen + 1
            TransitionType.BACK -> countChildScreen - 1
            TransitionType.REPLACE -> countChildScreen
            TransitionType.RESET -> 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screen = navigator.screenResolver.getScreen<Screens.NavigationController>(this)
        requireNavigationContextChanger().setNavigationContext(this)
        for ((index, item) in screen.screens.withIndex()) {
            if (index == 0) navigator.reset(item)
            else navigator.goForward(item)
        }
    }
}