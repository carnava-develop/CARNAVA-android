package com.carnava.android

import android.app.Application
import androidx.room.Room
import com.carnava.android.app.data.AppDatabase
import com.carnava.android.user.data.local.UserDao
import me.aartikov.alligator.AndroidNavigator

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        navigator = AndroidNavigator(com.carnava.android.core.navigation.AppNavigationFactory())
        appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .build()
        userDao = appDatabase.userDao()
    }

    companion object {
        lateinit var navigator: AndroidNavigator
        lateinit var appDatabase: AppDatabase
        lateinit var userDao: UserDao
    }
}