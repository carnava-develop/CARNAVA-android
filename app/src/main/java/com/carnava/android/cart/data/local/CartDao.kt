package com.carnava.android.cart.data.local

import androidx.room.*
import com.carnava.android.cart.data.models.CartProductEntity

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    suspend fun loadAllProduct(): List<CartProductEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: CartProductEntity)

    @Delete
    suspend fun deleteProduct(product: CartProductEntity)
}