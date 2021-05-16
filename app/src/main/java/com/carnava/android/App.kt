package com.carnava.android

import android.app.Application
import androidx.room.Room
import com.carnava.android.app.data.AppDatabase
import com.carnava.android.auth.data.local.AuthPrefs
import com.carnava.android.auth.data.repository.AuthRepositoryImpl
import com.carnava.android.auth.domain.repository.AuthRepository
import com.carnava.android.cart.data.local.CartDao
import com.carnava.android.cart.data.repository.CartRepositoryImpl
import com.carnava.android.cart.domain.repository.CartRepository
import com.carnava.android.category.data.local.CategoryDao
import com.carnava.android.category.data.repository.CategoryRepositoryImpl
import com.carnava.android.category.domain.repository.CategoryRepository
import com.carnava.android.core.navigation.AppNavigationFactory
import com.carnava.android.core.utils.EventBus
import com.carnava.android.favorite.data.local.FavoriteDao
import com.carnava.android.favorite.data.repositories.FavoriteRepositoryImpl
import com.carnava.android.favorite.domain.repositories.FavoriteRepository
import com.carnava.android.product.data.local.ProductDao
import com.carnava.android.product.data.repositories.ProductRepositoryImpl
import com.carnava.android.product.domain.repositories.ProductRepository
import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.data.repositories.UserRepositoryImpl
import com.carnava.android.user.domain.repositories.UserRepository
import com.chibatching.kotpref.Kotpref
import me.aartikov.alligator.AndroidNavigator
import me.aartikov.alligator.ScreenResolver
import me.aartikov.alligator.navigationfactories.NavigationFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        navigator = AndroidNavigator(AppNavigationFactory())
        screenResolver = navigator.screenResolver
        navigationFactory = navigator.navigationFactory

        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .build()
        userDao = appDatabase.userDao()
        categoryDao = appDatabase.categoryDao()
        productDao = appDatabase.productDao()
        cartDao = appDatabase.cartDao()
        favoriteDao = appDatabase.favoriteDao()

        Kotpref.init(this)
        authPrefs = AuthPrefs()

        eventBus = EventBus()

        categoryRepository = CategoryRepositoryImpl(categoryDao)
        userRepository = UserRepositoryImpl(userDao)
        authRepository = AuthRepositoryImpl(authPrefs)
        productRepository = ProductRepositoryImpl(productDao)
        cartRepository = CartRepositoryImpl(cartDao, authPrefs)
        favoriteRepository = FavoriteRepositoryImpl(favoriteDao, authPrefs)
    }

    companion object {
        lateinit var navigator: AndroidNavigator
        lateinit var screenResolver: ScreenResolver
        lateinit var navigationFactory: NavigationFactory

        private lateinit var appDatabase: AppDatabase
        private lateinit var userDao: UserDao
        private lateinit var categoryDao: CategoryDao
        private lateinit var productDao: ProductDao
        private lateinit var cartDao: CartDao
        private lateinit var favoriteDao: FavoriteDao

        private lateinit var authPrefs: AuthPrefs

        lateinit var eventBus: EventBus

        lateinit var categoryRepository: CategoryRepository
        lateinit var userRepository: UserRepository
        lateinit var authRepository: AuthRepository
        lateinit var productRepository: ProductRepository
        lateinit var cartRepository: CartRepository
        lateinit var favoriteRepository: FavoriteRepository
    }
}