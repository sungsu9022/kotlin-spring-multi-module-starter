package com.starter.admin.app.user.mapper

import com.starter.admin.app.user.model.UserCreateRequest
import com.starter.core.rdb.domain.user.repository.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserModelMapper  {
    companion object {
        val INSTANCE = Mappers.getMapper(UserModelMapper::class.java)
    }

    fun convertToEntity(
        userCreateRequest: UserCreateRequest,
        uuid: String
    ): UserEntity {
        return UserEntity(
            userName = userCreateRequest.userName,
            email = userCreateRequest.email,
            uuid = uuid,
        )
    }


}

