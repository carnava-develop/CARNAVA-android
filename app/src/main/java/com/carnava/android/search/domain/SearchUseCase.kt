package com.carnava.android.search.domain

import com.carnava.android.App
import com.carnava.android.product.domain.models.ProductModel

class SearchUseCase {

    suspend operator fun invoke(query: String): List<ProductModel> {
        val products = App.productRepository.loadAllProducts()
        val favorites = App.favoriteRepository.loadProducts()
        val searchResult = products.filter { it.title.contains(query, true) }
        val result = mutableListOf<ProductModel>()
        searchResult.forEach { product ->
            result.add(favorites.find { it.identification == product.identification } ?: product)
        }
        return result
    }
}