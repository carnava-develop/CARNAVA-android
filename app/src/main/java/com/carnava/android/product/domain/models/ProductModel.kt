package com.carnava.android.product.domain.models

data class ProductModel(
    override val identification: Int,
    override val idCategory: Int,
    override val title: String,
    override val image: Int,
    override val price: Int,
    override val isCart: Boolean,
    override val isFavorite: Boolean,
) : Product(identification, idCategory, title, image, price, isCart, isFavorite)