package com.carnava.android.cart.domain.usecases

import com.carnava.android.App

class LoadCartUseCase {
    suspend operator fun invoke() = App.cartRepository.loadCart()
}