package com.carnava.android.cart.domain.usecases

import com.carnava.android.App
import com.carnava.android.core.utils.EventBus
import com.carnava.android.product.domain.models.ProductModel

class RemoveProductFromCartUseCase {
    suspend operator fun invoke(product: ProductModel) {
        App.cartRepository.removeProduct(product)
            .also {
                App.eventBus.sendEvent(EventBus.Events.REMOVE_FROM_CART, product)
            }
    }
}