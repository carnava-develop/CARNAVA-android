package com.carnava.android.favorite.domain.usecases

import com.carnava.android.App
import com.carnava.android.product.domain.models.ProductModel

class AddIntoFavoriteUseCase {
    suspend operator fun invoke(product: ProductModel) {
        App.favoriteRepository.saveFavorite(product)
    }
}