package com.carnava.android.product.domain.models

import androidx.annotation.DrawableRes
import com.carnava.android.core.utils.Identification

data class ProductModel(
    override val id: Int,
    @DrawableRes val image: Int,
    val title: String,
    val price: Double,
    val isFavorite: Boolean
) : Identification