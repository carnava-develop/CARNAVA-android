package com.carnava.android.category.domain.models

import com.carnava.android.core.utils.Identification

abstract class Category(
    override val identification: Int,
    open val title: String,
    open val baseCategory: Int? = null,
    open val image: Int? = null
) : Identification