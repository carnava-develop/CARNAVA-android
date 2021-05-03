package com.carnava.android.user.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "email") @PrimaryKey val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "name") val name: String,
)