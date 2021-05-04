package com.carnava.android.product.domain.mappers

import com.carnava.android.cart.data.models.CartProductEntity
import com.carnava.android.product.domain.models.ProductModel

fun CartProductEntity.toProductModel() = ProductModel(id, title, description, price)
fun List<CartProductEntity>.toProductsModels() = map { it.toProductModel() }

fun ProductModel.toCartProductEntity() = CartProductEntity(id, title, description, price)
fun List<ProductModel>.toCartProductsEntities() = map { it.toCartProductEntity() }