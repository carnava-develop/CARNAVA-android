package com.carnava.android.core.navigation.fragment_controllers

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainerView
import com.carnava.android.App
import com.carnava.android.core.extensions.requireNavigationContextChanger
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.navigation.controllers.NavigationControllerContract
import com.carnava.android.core.ui.BaseFragment
import me.aartikov.alligator.DestinationType
import me.aartikov.alligator.Screen
import me.aartikov.alligator.TransitionType

class NavigationControllerFragment : BaseFragment(),
    NavigationControllerContract {

    private val navigator = App.navigator

    private val navigationContainer by lazy {
        FragmentContainerView(requireContext()).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    var countChildScreen = 0
    override fun getContainerId(): Int = navigationContainer.id
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