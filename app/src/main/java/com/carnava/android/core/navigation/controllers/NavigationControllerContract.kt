package com.carnava.android.core.navigation.controllers

import me.aartikov.alligator.DestinationType
import me.aartikov.alligator.Screen
import me.aartikov.alligator.TransitionType
import com.carnava.android.core.navigation.global.ContainerProvider
import com.carnava.android.core.navigation.global.OnBackPressedProvider

interface NavigationControllerContract : ContainerProvider, OnBackPressedProvider {
    fun onTransactionScreen(
        transitionType: TransitionType,
        destinationType: DestinationType,
        screenClassFrom: Class<out Screen>?,
        screenClassTo: Class<out Screen>?
    ) {
    }
}