package com.carnava.android.category.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carnava.android.category.domain.models.Category

@Entity(tableName = "categories")
data class CategoryEntity(
    @ColumnInfo(name = "identification") @PrimaryKey override val identification: Int,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "base_category") override val baseCategory: Int?,
    @ColumnInfo(name = "image") override val image: Int?
) : Category(identification, title, baseCategory, image)
