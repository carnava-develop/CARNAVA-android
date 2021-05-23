package com.carnava.android.cart.domain.repository

import com.carnava.android.product.domain.models.ProductModel

interface CartRepository {
    abstract suspend fun addProduct(product: ProductModel)
    suspend fun removeProduct(product: ProductModel)
}