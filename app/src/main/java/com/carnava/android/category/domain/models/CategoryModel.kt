package com.carnava.android.category.domain.models

data class CategoryModel(
    override val identification: Int,
    override val title: String,
    override val baseCategory: Int? = null,
    override val image: Int? = null
) : Category(identification, title, baseCategory, image)