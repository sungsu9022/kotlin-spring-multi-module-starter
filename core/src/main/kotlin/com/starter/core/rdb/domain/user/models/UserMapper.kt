package com.starter.core.rdb.domain.user.models

import com.starter.core.common.mapper.GenericMapper
import com.starter.core.rdb.domain.user.repository.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface UserMapper : GenericMapper<UserEntity, UserVO> {
    companion object {
        val INSTANCE = Mappers.getMapper(UserMapper::class.java)
    }
}
