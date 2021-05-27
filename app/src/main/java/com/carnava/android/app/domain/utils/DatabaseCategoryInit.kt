package com.carnava.android.app.domain.utils

import com.carnava.android.App
import com.carnava.android.R
import com.carnava.android.category.domain.models.CategoryModel

object DatabaseCategoryInit {
    suspend operator fun invoke() {
        val categories = listOf(
            CategoryModel(0, "Одежда", image = R.drawable.ic_logo),
            CategoryModel(1, "Футболки", 0),
            CategoryModel(2, "Шорты", 0),
        )
        categories.forEach { App.categoryRepository.saveCategory(it) }
    }
}