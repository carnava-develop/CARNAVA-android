package com.carnava.android

import android.app.Application
import androidx.room.Room
import com.carnava.android.app.data.AppDatabase
import com.carnava.android.core.navigation.AppNavigationFactory
import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.data.repositories.UserRepositoryImpl
import com.carnava.android.user.domain.repositories.UserRepository
import me.aartikov.alligator.AndroidNavigator

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        navigator = AndroidNavigator(AppNavigationFactory())

        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .build()
        userDao = appDatabase.userDao()

        userRepository = UserRepositoryImpl(userDao)
    }

    companion object {
        lateinit var navigator: AndroidNavigator

        lateinit var appDatabase: AppDatabase
        lateinit var userDao: UserDao

        lateinit var userRepository: UserRepository
    }
}