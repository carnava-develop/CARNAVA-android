package com.carnava.android.product.domain.repositories

import com.carnava.android.product.data.models.ProductEntity

interface ProductRepository {
    suspend fun loadAllProducts(): List<ProductEntity>
}