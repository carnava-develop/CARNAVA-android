package com.carnava.android.favorite.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carnava.android.product.domain.models.Product

@Entity(tableName = "favorites")
data class FavoriteProductEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_email") val userEmail: String,
    @ColumnInfo(name = "identification") override val identification: Int,
    @ColumnInfo(name = "title") override val title: String,
    @ColumnInfo(name = "image") override val image: Int,
    @ColumnInfo(name = "price") override val price: Int,
    @ColumnInfo(name = "isCart") override val isCart: Boolean,
    @ColumnInfo(name = "isFavorite") override val isFavorite: Boolean
) : Product(identification, title, image, price, isCart, isFavorite)