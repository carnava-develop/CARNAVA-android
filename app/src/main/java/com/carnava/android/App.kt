package com.carnava.android

import android.app.Application
import androidx.room.Room
import com.carnava.android.app.data.AppDatabase
import com.carnava.android.auth.data.local.AuthPrefs
import com.carnava.android.auth.data.repository.AuthRepositoryImpl
import com.carnava.android.auth.domain.repository.AuthRepository
import com.carnava.android.core.navigation.AppNavigationFactory
import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.data.repositories.UserRepositoryImpl
import com.carnava.android.user.domain.repositories.UserRepository
import com.chibatching.kotpref.Kotpref
import me.aartikov.alligator.AndroidNavigator

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        navigator = AndroidNavigator(AppNavigationFactory())

        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .build()
        userDao = appDatabase.userDao()

        Kotpref.init(this)
        authPrefs = AuthPrefs()

        userRepository = UserRepositoryImpl(userDao)
        authRepository = AuthRepositoryImpl(authPrefs)
    }

    companion object {
        lateinit var navigator: AndroidNavigator

        private lateinit var appDatabase: AppDatabase
        private lateinit var userDao: UserDao

        private lateinit var authPrefs: AuthPrefs

        lateinit var userRepository: UserRepository
        lateinit var authRepository: AuthRepository
    }
}