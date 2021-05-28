package com.carnava.android.product.domain.usecases

import com.carnava.android.App
import com.carnava.android.product.domain.models.ProductModel

class LoadProductByCategory {
    suspend operator fun invoke(idCategory: Int): List<ProductModel> {
        return App.productRepository.loadProductsByCategory(idCategory)
    }
}