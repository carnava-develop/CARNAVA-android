package com.carnava.android.cart.domain.repository

import com.carnava.android.product.domain.models.ProductModel

interface CartRepository {
    abstract fun addProduct(product: ProductModel)
}