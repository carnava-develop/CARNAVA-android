package com.carnava.android.favorite.domain.repositories

import com.carnava.android.product.domain.models.ProductModel

interface FavoriteRepository {
    suspend fun saveFavorite(product: ProductModel)
    suspend fun deleteFavorite(product: ProductModel)
}