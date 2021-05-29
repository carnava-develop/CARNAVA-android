package com.carnava.android.product.domain.mappers

import com.carnava.android.product.data.models.ProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun List<ProductEntity>.toProductsModels() = map { it.toProductModel() }
fun ProductEntity.toProductModel() =
    ProductModel(identification, idCategory, title, image, price, isCart, isFavorite)

fun List<ProductModel>.toProductsEntities() = map { it.toProductEntity() }
fun ProductModel.toProductEntity() =
    ProductEntity(identification, idCategory, title, image, price, isCart, isFavorite)
