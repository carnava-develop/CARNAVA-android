package com.carnava.android.favorite.domain.usecases

import com.carnava.android.App

class LoadFavoritesProductsUseCase {
    suspend operator fun invoke() = App.favoriteRepository.loadProducts()
}