package com.carnava.android.user.domain.mappers

import com.carnava.android.user.data.model.UserEntity
import com.carnava.android.user.domain.models.UserModel

fun UserEntity.toUserModel() = UserModel(email, password, name)
fun List<UserEntity>.toUsersModels() = map { it.toUserModel() }

fun UserModel.toUserEntity() = UserEntity(email, password, name)
fun List<UserModel>.toUsersEntities() = map { it.toUserEntity() }
