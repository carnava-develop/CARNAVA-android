package com.carnava.android.core.navigation

import androidx.annotation.MenuRes
import me.aartikov.alligator.Screen
import java.io.Serializable

object Screens {
    object Application : Screen, Serializable

    data class TabNavigationController(
        @MenuRes val menuId: Int,
        val screen1: Pair<Int, Screen>? = null,
        val screen2: Pair<Int, Screen>? = null,
        val screen3: Pair<Int, Screen>? = null,
        val screen4: Pair<Int, Screen>? = null,
        val screen5: Pair<Int, Screen>? = null,
    ) : Screen, Serializable

    data class NavigationController(
        val screens: List<Screen>
    ) : Screen, Serializable
}