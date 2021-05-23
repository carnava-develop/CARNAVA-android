package com.carnava.android.product.data.repositories

import com.carnava.android.product.data.local.ProductDao
import com.carnava.android.product.domain.mappers.toProductsModels
import com.carnava.android.product.domain.repositories.ProductRepository

class ProductRepositoryImpl(private val productDao: ProductDao) : ProductRepository {

    override suspend fun loadAllProducts() = productDao.loadAllProducts().toProductsModels()

}