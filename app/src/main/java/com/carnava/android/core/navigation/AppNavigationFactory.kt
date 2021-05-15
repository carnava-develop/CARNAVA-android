package com.carnava.android.core.navigation

import com.carnava.android.app.presentation.AppActivity
import com.carnava.android.auth.presentation.sign_in.SignInFragment
import com.carnava.android.auth.presentation.sign_up.SignUpDialog
import com.carnava.android.cart.presentation.CartFragment
import com.carnava.android.core.navigation.fragment_controllers.NavigationControllerFragment
import com.carnava.android.core.navigation.fragment_controllers.TabNavigationControllerFragment
import com.carnava.android.favorite.presentation.FavoriteFragment
import com.carnava.android.home.presentation.HomeFragment
import com.carnava.android.search.presentation.SearchFragment
import com.carnava.android.user.presentation.profile.ProfileFragment
import me.aartikov.alligator.navigationfactories.RegistryNavigationFactory

class AppNavigationFactory : RegistryNavigationFactory() {
    init {
        registerActivity(Screens.Application::class.java, AppActivity::class.java)
        registerFragment(
            Screens.TabNavigationController::class.java,
            TabNavigationControllerFragment::class.java
        )
        registerFragment(
            Screens.NavigationController::class.java,
            NavigationControllerFragment::class.java
        )

        registerFragment(Screens.SignIn::class.java, SignInFragment::class.java)
        registerDialogFragment(Screens.SignUp::class.java, SignUpDialog::class.java)

        registerFragment(Screens.Home::class.java, HomeFragment::class.java)
        registerFragment(Screens.Search::class.java, SearchFragment::class.java)
        registerFragment(Screens.Cart::class.java, CartFragment::class.java)
        registerFragment(Screens.Favorite::class.java, FavoriteFragment::class.java)
        registerFragment(Screens.Profile::class.java, ProfileFragment::class.java)
    }
}