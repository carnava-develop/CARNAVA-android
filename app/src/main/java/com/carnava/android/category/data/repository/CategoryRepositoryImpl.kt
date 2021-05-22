package com.carnava.android.category.data.repository

import com.carnava.android.category.data.local.CategoryDao
import com.carnava.android.category.domain.mappers.toCategoriesModels
import com.carnava.android.category.domain.mappers.toCategoryEntity
import com.carnava.android.category.domain.models.CategoryModel
import com.carnava.android.category.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val categoryDao: CategoryDao) : CategoryRepository {

    override suspend fun loadCategories(): List<CategoryModel> {
        return categoryDao.selectAllCategories().toCategoriesModels()
    }

    override suspend fun loadCategories(baseCategory: Int): List<CategoryModel> {
        return categoryDao.selectCategories(baseCategory).toCategoriesModels()
    }

    override suspend fun saveCategory(categoryModel: CategoryModel) {
        categoryDao.insertCategory(categoryModel.toCategoryEntity())
    }
}