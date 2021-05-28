package com.carnava.android.product.domain.repositories

import com.carnava.android.product.domain.models.ProductModel

interface ProductRepository {
    suspend fun loadAllProducts(): List<ProductModel>
    suspend fun loadProductsByCategory(idCategory: Int): List<ProductModel>
}