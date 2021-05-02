package com.carnava.android.user.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carnava.android.user.data.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun loadAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun loadUser(email: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveUser(userEntity: UserEntity)

}