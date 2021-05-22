package com.carnava.android.app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carnava.android.cart.data.local.CartDao
import com.carnava.android.cart.data.models.CartProductEntity
import com.carnava.android.category.data.local.CategoryDao
import com.carnava.android.category.data.models.CategoryEntity
import com.carnava.android.favorite.data.local.FavoriteDao
import com.carnava.android.favorite.data.models.FavoriteProductEntity
import com.carnava.android.product.data.local.ProductDao
import com.carnava.android.product.data.models.ProductEntity
import com.carnava.android.user.data.local.UserDao
import com.carnava.android.user.data.model.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ProductEntity::class,
        CartProductEntity::class,
        FavoriteProductEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun categoryDao(): CategoryDao
}