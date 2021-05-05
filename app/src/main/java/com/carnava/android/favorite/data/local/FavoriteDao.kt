package com.carnava.android.favorite.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carnava.android.favorite.data.models.FavoriteProductEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveProduct(productEntity: FavoriteProductEntity)

    @Query("DELETE FROM favorites WHERE id_product = :idProduct AND email_user = :emailUser")
    suspend fun deleteProduct(idProduct: Int, emailUser: String)
}