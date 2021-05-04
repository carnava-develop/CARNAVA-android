package com.carnava.android.favorite.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.carnava.android.favorite.data.models.FavoriteProductEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveProduct(productEntity: FavoriteProductEntity)

    @Delete
    suspend fun deleteProduct(productEntity: FavoriteProductEntity)
}