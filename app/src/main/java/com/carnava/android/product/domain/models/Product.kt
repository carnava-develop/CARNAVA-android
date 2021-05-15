package com.carnava.android.product.domain.models

import com.carnava.android.core.utils.Identification

abstract class Product(
    override val identification: Int,
    open val title: String,
    open val image: Int,
    open val price: Int,
) : Identification