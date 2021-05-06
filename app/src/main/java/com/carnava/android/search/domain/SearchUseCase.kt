package com.carnava.android.search.domain

import com.carnava.android.product.data.models.ProductEntity
import com.carnava.android.product.domain.repositories.ProductRepository

class SearchUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(query: String): List<ProductEntity> {
        val products = productRepository.loadAllProducts()
        return products.filter { query.contains(query, true) }
    }
}