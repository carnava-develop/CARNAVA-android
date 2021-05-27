package com.carnava.android.product.domain.mappers

import com.carnava.android.cart.data.models.CartProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun CartProductEntity.toProductModel() =
    ProductModel(identification, idCategory, title, image, price, isCart, isFavorite)

fun List<CartProductEntity>.toProductsModels() = map { it.toProductModel() }

private const val AUTO_GENERATED_ID = 0
fun ProductModel.toCartProductEntity(userEmail: String) = CartProductEntity(
    id = AUTO_GENERATED_ID,
    userEmail = userEmail,
    idCategory = idCategory,
    identification = identification,
    title = title,
    image = image,
    price = price,
    isCart = isCart,
    isFavorite = isFavorite
)

fun List<ProductModel>.toCartProductsEntities(userEmail: String) =
    map { it.toCartProductEntity(userEmail) }