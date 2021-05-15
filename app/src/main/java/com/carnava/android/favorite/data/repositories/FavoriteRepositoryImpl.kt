package com.carnava.android.favorite.data.repositories

import com.carnava.android.auth.data.local.AuthPrefs
import com.carnava.android.favorite.data.local.FavoriteDao
import com.carnava.android.favorite.domain.repositories.FavoriteRepository
import com.carnava.android.product.domain.mappers.toFavoriteProductEntity
import com.carnava.android.product.domain.mappers.toProductsModels
import com.carnava.android.product.domain.models.ProductModel

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao,
    private val authPrefs: AuthPrefs
) : FavoriteRepository {
    override suspend fun saveFavorite(product: ProductModel) {
        favoriteDao.saveProduct(product.toFavoriteProductEntity(authPrefs.email))
    }

    override suspend fun deleteFavorite(product: ProductModel) {
        favoriteDao.deleteProduct(product.identification, authPrefs.email)
    }

    override suspend fun loadProducts(): List<ProductModel> {
        return favoriteDao.loadProducts(authPrefs.email).toProductsModels()
    }
}