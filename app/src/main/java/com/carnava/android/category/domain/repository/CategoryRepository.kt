package com.carnava.android.category.domain.repository

import com.carnava.android.category.domain.models.CategoryModel

interface CategoryRepository {
    suspend fun loadCategories(): List<CategoryModel>
    suspend fun loadCategories(baseCategory: Int): List<CategoryModel>
    suspend fun saveCategory(categoryModel: CategoryModel)
}