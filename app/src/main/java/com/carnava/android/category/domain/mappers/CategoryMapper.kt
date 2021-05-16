package com.carnava.android.category.domain.mappers

import com.carnava.android.category.data.models.CategoryEntity
import com.carnava.android.category.domain.models.CategoryModel

fun CategoryEntity.toCategoryModel() = CategoryModel(identification, title, baseCategory, image)
fun List<CategoryEntity>.toCategoriesModels() = map { it.toCategoryModel() }

fun CategoryModel.toCategoryEntity() = CategoryEntity(identification, title, baseCategory, image)
fun List<CategoryModel>.toCategoriesEntities() = map { it.toCategoryEntity() }

