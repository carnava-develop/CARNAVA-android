package com.carnava.android.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carnava.android.cart.data.local.CartDao
import com.carnava.android.favorite.data.local.FavoriteDao
import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao
}