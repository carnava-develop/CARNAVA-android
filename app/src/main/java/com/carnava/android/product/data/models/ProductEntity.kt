package com.carnava.android.product.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carnava.android.product.domain.models.Product

@Entity(tableName = "products")
data class ProductEntity(
    @ColumnInfo(name = "identification") @PrimaryKey override val identification: Int,
    @ColumnInfo(name = "user_email") val userEmail: String,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "image") override val image: Int,
    @ColumnInfo(name = "price") override val price: Int,
) : Product(identification, title, image, price)