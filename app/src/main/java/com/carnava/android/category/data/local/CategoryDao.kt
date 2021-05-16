package com.carnava.android.category.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carnava.android.category.data.models.CategoryEntity

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    fun selectAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE base_category = :baseCategory")
    fun selectCategories(baseCategory: Int): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryEntity)
}