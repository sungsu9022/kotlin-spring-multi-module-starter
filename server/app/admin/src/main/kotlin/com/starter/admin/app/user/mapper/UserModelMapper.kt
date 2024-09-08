package com.starter.admin.app.user.mapper

import com.starter.admin.app.user.model.UserSearchCondition
import com.starter.admin.repository.user.UserEntity
import com.starter.core.models.user.User
import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest
import com.starter.core.models.user.UserStatus
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserModelMapper  {
    companion object {
        val INSTANCE = Mappers.getMapper(UserModelMapper::class.java)
    }

    fun toEntity(
        request: UserCreateRequest,
        uuid: String,
        userStatus: UserStatus,
    ): UserEntity

    fun toCondition(
        request: UserSearchRequest,
    ): UserSearchCondition

    fun toResponse(
        user: User
    ): UserResponse

}

