package com.carnava.android.search.domain

import com.carnava.android.App
import com.carnava.android.product.domain.models.ProductModel

class SearchUseCase {

    suspend operator fun invoke(query: String): List<ProductModel> {
        val products = App.productRepository.loadAllProducts()
        return products.filter { query.contains(query, true) }
    }
}