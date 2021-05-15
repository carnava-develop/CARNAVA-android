package com.carnava.android.core.navigation.fragment_controllers

import android.os.Bundle
import android.view.View
import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.core.extensions.requireNavigationContextChanger
import com.carnava.android.core.navigation.Screens
import com.carnava.android.core.navigation.controllers.TabNavigationControllerContract
import com.carnava.android.core.ui.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.aartikov.alligator.Screen
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.NavigationFactory
import me.aartikov.alligator.screenswitchers.FragmentScreenSwitcher
import me.aartikov.alligator.screenswitchers.ScreenSwitcher

class TabNavigationControllerFragment : BaseFragment(R.layout.fragment_tab_navigation_controller),
    TabNavigationControllerContract {

    private val navigator = App.navigator
    private val screenResolver: ScreenResolver = App.screenResolver
    private val navigationFactory: NavigationFactory = App.navigationFactory

    private val screenArgs by lazy { screenResolver.getScreen<Screens.TabNavigationController>(this) }
    private val menuId: Int by lazy { screenArgs.menuId }
    private val screen1: Pair<Int, Screen>? by lazy { screenArgs.screen1 }
    private val screen2: Pair<Int, Screen>? by lazy { screenArgs.screen2 }
    private val screen3: Pair<Int, Screen>? by lazy { screenArgs.screen3 }
    private val screen4: Pair<Int, Screen>? by lazy { screenArgs.screen4 }
    private val screen5: Pair<Int, Screen>? by lazy { screenArgs.screen5 }
    private val screens by lazy { listOf(screen1, screen2, screen3, screen4, screen5) }
    private val mainScreen by lazy { screen1 ?: screen2 ?: screen3 ?: screen4 ?: screen5 }
    private var currentSwitchScreen: Pair<Int, Screen>? = null

    private val screenSwitcher by lazy {
        FragmentScreenSwitcher(
            navigationFactory,
            childFragmentManager,
            R.id.container_tab_navigation_controller
        )
    }

    private lateinit var bottomNavigation: BottomNavigationView

    override fun getScreenSwitcher(): ScreenSwitcher = screenSwitcher
    override fun getContainerId(): Int = R.id.container_tab_navigation_controller
    override fun onSwitchScreen(screenFrom: Screen?, screenTo: Screen) {
        currentSwitchScreen = screens.find { it?.second == screenTo }
        bottomNavigation.menu.findItem(currentSwitchScreen?.first!!).isChecked = true
    }

    override fun canGoBack(): Boolean = currentSwitchScreen != mainScreen
    override fun onBackPressed() {
        requireNavigationContextChanger().setNavigationContext(this)
        navigator.switchTo(mainScreen?.second!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentSwitchScreen = mainScreen
        requireNavigationContextChanger().setNavigationContext(this)
        navigator.switchTo(mainScreen?.second!!)
    }

    override fun setupView(view: View) {
        bottomNavigation = view.findViewById(R.id.bottom_navigation_tab_controller)
        bottomNavigation.apply {
            menu.clear()
            inflateMenu(menuId)
            setOnNavigationItemSelectedListener { menuItem ->
                requireNavigationContextChanger()
                    .setNavigationContext(this@TabNavigationControllerFragment)
                val screen = screens.find { it?.first == menuItem.itemId }
                navigator.switchTo(screen?.second!!)
                true
            }
        }
    }
}
