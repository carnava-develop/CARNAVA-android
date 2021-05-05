package com.carnava.android.favorite.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProductEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "id_product") val idProduct: Int,
    @ColumnInfo(name = "email_user") val emailUser: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Double,
)