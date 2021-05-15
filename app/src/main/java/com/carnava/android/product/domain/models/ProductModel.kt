package com.carnava.android.product.domain.models

data class ProductModel(
    override val identification: Int,
    override val title: String,
    override val image: Int,
    override val price: Int,
) : Product(identification, title, image, price)