package com.carnava.android.product.domain.mappers

import com.carnava.android.favorite.data.models.FavoriteProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun FavoriteProductEntity.toProductModel() =
    ProductModel(identification, title, image, price, isCart, isFavorite)

fun List<FavoriteProductEntity>.toProductsModels() = map { it.toProductModel() }

private const val AUTO_GENERATED_ID = 0
fun ProductModel.toFavoriteProductEntity(userEmail: String) = FavoriteProductEntity(
    id = AUTO_GENERATED_ID,
    userEmail = userEmail,
    identification = identification,
    title = title,
    image = image,
    price = price,
    isCart = isCart,
    isFavorite = true
)

fun List<ProductModel>.toFavoritesProductsEntities(userEmail: String) =
    map { it.toFavoriteProductEntity(userEmail) }