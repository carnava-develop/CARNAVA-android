package com.carnava.android.product.domain.usecases

import com.carnava.android.App

class LoadProductsUseCase {
    suspend operator fun invoke() = App.productRepository.loadAllProducts()
}