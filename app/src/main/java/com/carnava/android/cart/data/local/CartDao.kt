package com.carnava.android.cart.data.local

import androidx.room.Dao
import androidx.room.Query
import com.carnava.android.cart.data.models.CartProductEntity

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun loadAllProduct(): List<CartProductEntity>
}