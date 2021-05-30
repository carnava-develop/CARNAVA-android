package com.carnava.android.cart.data.repository

import com.carnava.android.auth.data.local.AuthPrefs
import com.carnava.android.cart.data.local.CartDao
import com.carnava.android.cart.domain.repository.CartRepository
import com.carnava.android.product.domain.mappers.toCartProductEntity
import com.carnava.android.product.domain.models.ProductModel

class CartRepositoryImpl(
    private val cartDao: CartDao,
    private val authPrefs: AuthPrefs
) : CartRepository {

    override suspend fun addProduct(product: ProductModel) {
        cartDao.insertProduct(product.toCartProductEntity(authPrefs.email))
    }

    override suspend fun removeProduct(product: ProductModel) {
        cartDao.deleteProduct(product.toCartProductEntity(authPrefs.email))
    }

    override suspend fun clearCart() {
        cartDao.loadAllProduct().forEach { cartDao.deleteProduct(it) }
    }
}