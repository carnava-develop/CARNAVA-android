package com.carnava.android.product.domain.mappers

import com.carnava.android.favorite.data.models.FavoriteProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun FavoriteProductEntity.toProductModel() = ProductModel(id, title, description, price)
fun List<FavoriteProductEntity>.toProductsModels() = map { it.toProductModel() }

fun ProductModel.toFavoriteProductEntity(email: String): FavoriteProductEntity {
    return FavoriteProductEntity(0, id, email, title, description, price)
}

fun List<ProductModel>.toFavoritesProductsEntities(email: String): List<FavoriteProductEntity> {
    return map { it.toFavoriteProductEntity(email) }
}