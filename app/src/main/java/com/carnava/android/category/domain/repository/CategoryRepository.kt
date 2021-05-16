package com.carnava.android.category.domain.repository

import com.carnava.android.category.domain.models.CategoryModel

interface CategoryRepository {
    fun loadCategories(): List<CategoryModel>
    fun loadCategories(baseCategory: Int): List<CategoryModel>
    fun saveCategory(categoryModel: CategoryModel)
}