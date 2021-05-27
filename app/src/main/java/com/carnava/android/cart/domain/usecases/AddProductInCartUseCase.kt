package com.carnava.android.cart.domain.usecases

import com.carnava.android.App
import com.carnava.android.product.domain.models.ProductModel

class AddProductInCartUseCase {
    suspend operator fun invoke(product: ProductModel) = App.cartRepository.addProduct(product)
}