package com.carnava.android.product.domain.mappers

import com.carnava.android.product.data.models.ProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun ProductEntity.toProductModel() = ProductModel(id, title, description, price)
fun List<ProductEntity>.toProductsModels() = map { it.toProductModel() }

fun ProductModel.toProductEntity() = ProductEntity(id, title, description, price)
fun List<ProductModel>.toProductsEntities() = map { it.toProductEntity() }