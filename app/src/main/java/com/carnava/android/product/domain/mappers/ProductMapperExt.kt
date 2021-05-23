package com.carnava.android.product.domain.mappers

import com.carnava.android.product.data.models.ProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun ProductEntity.toProductModel() =
    ProductModel(identification, title, image, price, isCart, isFavorite)

fun List<ProductEntity>.toProductsModels() = map { it.toProductModel() }
