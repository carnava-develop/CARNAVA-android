package com.carnava.android.app.domain.utils

import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.product.domain.models.ProductModel

object DatabaseProductInit {
    suspend operator fun invoke() {
        val products = listOf(
            ProductModel(
                identification = 0,
                idCategory = 1,
                title = "Product0",
                image = R.drawable.ic_logo,
                price = 220,
                isCart = false,
                isFavorite = false
            ),
            ProductModel(
                identification = 1,
                idCategory = 1,
                title = "Product1",
                image = R.drawable.ic_logo,
                price = 220,
                isCart = false,
                isFavorite = false
            ), ProductModel(
                identification = 2,
                idCategory = 1,
                title = "Product2",
                image = R.drawable.ic_logo,
                price = 220,
                isCart = false,
                isFavorite = false
            ), ProductModel(
                identification = 3,
                idCategory = 1,
                title = "Product3",
                image = R.drawable.ic_logo,
                price = 220,
                isCart = false,
                isFavorite = false
            ), ProductModel(
                identification = 4,
                idCategory = 1,
                title = "Product4",
                image = R.drawable.ic_logo,
                price = 220,
                isCart = false,
                isFavorite = false
            )
        )
        App.productRepository.addProducts(products)
    }
}