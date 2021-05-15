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

    override fun addProduct(product: ProductModel) {
        cartDao.insertProduct(product.toCartProductEntity())
    }
}