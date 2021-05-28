package com.carnava.android.product.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carnava.android.product.data.models.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM products")
    suspend fun loadAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id_category = :idCategory")
    suspend fun loadProductsByCategory(idCategory: Int): List<ProductEntity>
}