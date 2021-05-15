package com.carnava.android.core.navigation

import com.carnava.android.App
import com.carnava.android.R

object NavigationHelper {
    fun resetMainTabController() = App.navigator.reset(
        Screens.TabNavigationController(
            menuId = R.menu.main_bottom_navigation,
            screen1 = R.id.item_home to Screens.NavigationController(listOf(Screens.Home)),
            screen2 = R.id.item_search to Screens.NavigationController(listOf(Screens.Search)),
            screen3 = R.id.item_cart to Screens.NavigationController(listOf(Screens.Cart)),
            screen4 = R.id.item_favorite to Screens.NavigationController(listOf(Screens.Favorite)),
            screen5 = R.id.item_profile to Screens.NavigationController(listOf(Screens.Profile)),
        )
    )
}