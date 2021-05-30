package com.carnava.android.product.domain.mappers

import com.carnava.android.favorite.data.models.FavoriteProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun FavoriteProductEntity.toProductModel() =
    ProductModel(identification, idCategory, title, image, price, isCart, isFavorite)

fun List<FavoriteProductEntity>.toProductsModels() = map { it.toProductModel() }

private const val AUTO_GENERATED_ID = 0
fun ProductModel.toFavoriteProductEntity(userEmail: String) = FavoriteProductEntity(
    id = AUTO_GENERATED_ID,
    idCategory = idCategory,
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