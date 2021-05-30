package com.carnava.android.cart.domain.usecases

import com.carnava.android.App
import com.carnava.android.core.utils.EventBus

class ClearCartUseCase {
    suspend operator fun invoke() {
        App.cartRepository.clearCart()
            .also { App.eventBus.sendEvent(EventBus.Events.CLEAR_CART, Unit) }
    }
}