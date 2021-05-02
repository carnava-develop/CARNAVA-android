package com.carnava.android.app.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.carnava.android.App
import com.carnava.android.core.navigation.controllers.NavigationControllerContract
import com.carnava.android.core.navigation.controllers.TabNavigationControllerContract
import com.carnava.android.core.navigation.global.ContainerProvider
import com.carnava.android.core.navigation.global.NavigationContextChangerProvider
import com.carnava.android.core.navigation.global.NavigationScreenSwitcherProvider
import me.aartikov.alligator.NavigationContext
import me.aartikov.alligator.NavigationContextBinder
import me.aartikov.alligator.navigationfactories.NavigationFactory

class AppActivity : AppCompatActivity(),
    NavigationContextChangerProvider,
    DialogInterface.OnDismissListener {

    private val navigator = App.navigator
    private val navigationContextBinder: NavigationContextBinder = App.navigator
    private val navigationFactory: NavigationFactory = App.navigator.navigationFactory

    private val appContainer by lazy {
        FragmentContainerView(this).apply {
            id = View.generateViewId()
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(appContainer)
    }

    override fun onResume() {
        super.onResume()
        bindNavigationContext()
    }

    override fun onPause() {
        super.onPause()
        navigationContextBinder.unbind(this)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        bindNavigationContext()
    }

    override fun onBackPressed() {
        if (onBackPressedDispatcher.hasEnabledCallbacks()) {
            onBackPressedDispatcher.onBackPressed()
            return
        }

        val lastNavigationFragment = getLastNavigationFragment(supportFragmentManager.fragments)
        if (lastNavigationFragment == null) {
            navigator.goBack()
            return
        }
        val canGoBackNavigationFragment = getCanGoBackLastNavigationFragment(lastNavigationFragment)
        when {
            canGoBackNavigationFragment != null
                    && canGoBackNavigationFragment is NavigationControllerContract -> {
                canGoBackNavigationFragment.onBackPressed()
            }
            else -> {
                navigator.goBack()
            }
        }
    }

    override fun setNavigationContext(fragment: Fragment) {
        bindNavigationContext(fragment)
    }

    override fun setFirstTabNavigationContext() {
        bindNavigationContext(getFirstTabNavigationFragment(supportFragmentManager.fragments))
    }

    override fun setLastTabNavigationContext(lastFragment: Fragment) {
        bindNavigationContext(getLastTabNavigationFragment(lastFragment))
    }

    override fun defaultNavigationContext() {
        bindNavigationContext()
    }

    override fun resetNavigationContext() {
        bindNavigationContext(resetNavigationContext = true)
    }

    private fun bindNavigationContext(
        navigationFragment: Fragment? = null,
        resetNavigationContext: Boolean = false
    ) {
        val navigationContext = NavigationContext.Builder(this, navigationFactory)
            .fragmentNavigation(supportFragmentManager, appContainer.id)
            .transitionListener { _, _, _, _ ->
                if (getLastNavigationFragment(supportFragmentManager.fragments) != null) {
                    bindNavigationContext()
                }
            }
        if (resetNavigationContext) {
            navigationContextBinder.bind(navigationContext.build())
            return
        }
        val lastNavigationFragment = navigationFragment
            ?: getLastNavigationFragment(supportFragmentManager.fragments)
        if (lastNavigationFragment is ContainerProvider) {
            navigationContext.fragmentNavigation(
                lastNavigationFragment.childFragmentManager,
                lastNavigationFragment.getContainerId()
            )
        }
        if (lastNavigationFragment is NavigationScreenSwitcherProvider) {
            navigationContext
                .screenSwitcher(lastNavigationFragment.getScreenSwitcher())
                .screenSwitchingListener { screenFrom, screenTo ->
                    if (getLastNavigationFragment(supportFragmentManager.fragments) != null) {
                        bindNavigationContext()
                    }
                    lastNavigationFragment.onSwitchScreen(screenFrom, screenTo)
                }
        }
        if (lastNavigationFragment is NavigationControllerContract) {
            navigationContext.transitionListener { transitionType, destinationType, screenClassFrom, screenClassTo ->
                if (getLastNavigationFragment(supportFragmentManager.fragments) != null) {
                    bindNavigationContext()
                }
                lastNavigationFragment.onTransactionScreen(
                    transitionType = transitionType,
                    destinationType = destinationType,
                    screenClassFrom = screenClassFrom,
                    screenClassTo = screenClassTo
                )
            }
        }
        navigationContextBinder.bind(navigationContext.build())
    }

    private fun getLastNavigationFragment(fragments: List<Fragment>): Fragment? {
        val navigationFragment = fragments.findLast {
            it is NavigationControllerContract || it is TabNavigationControllerContract
        }

        val innerFragments = navigationFragment?.childFragmentManager?.fragments
        val haveInnerNavigation = innerFragments?.find {
            it is NavigationControllerContract || it is TabNavigationControllerContract
        } != null

        return if (haveInnerNavigation && navigationFragment != null) {
            getLastNavigationFragment(navigationFragment.childFragmentManager.fragments)
        } else {
            navigationFragment
        }
    }

    private fun getCanGoBackLastNavigationFragment(lastNavigationFragment: Fragment): Fragment? {
        when {
            lastNavigationFragment !is NavigationControllerContract
                    && lastNavigationFragment.parentFragment == null -> {
                return null
            }

            lastNavigationFragment !is NavigationControllerContract
                    && lastNavigationFragment.parentFragment != null -> {
                getCanGoBackLastNavigationFragment(lastNavigationFragment.requireParentFragment())
            }
        }

        return when {
            lastNavigationFragment is NavigationControllerContract
                    && lastNavigationFragment.canGoBack() -> {
                lastNavigationFragment
            }

            lastNavigationFragment is NavigationControllerContract
                    && !lastNavigationFragment.canGoBack()
                    && lastNavigationFragment.parentFragment != null -> {
                getCanGoBackLastNavigationFragment(lastNavigationFragment.requireParentFragment())
            }

            else -> null
        }
    }

    private fun getFirstTabNavigationFragment(fragments: List<Fragment>): Fragment? {
        val tabNavigationFragment = fragments.find { it is TabNavigationControllerContract }
        if (tabNavigationFragment != null) return tabNavigationFragment
        fragments.forEach { getFirstTabNavigationFragment(it.childFragmentManager.fragments) }
        return null
    }

    private fun getLastTabNavigationFragment(lastFragment: Fragment): Fragment? {
        val parentFragment = lastFragment.parentFragment ?: return null
        return if (parentFragment is TabNavigationControllerContract) parentFragment
        else getLastTabNavigationFragment(parentFragment)
    }
}