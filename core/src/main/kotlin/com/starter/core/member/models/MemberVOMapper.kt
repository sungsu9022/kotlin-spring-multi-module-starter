package com.starter.core.member.models

import com.starter.core.common.mapper.GenericMapper
import com.starter.core.member.repository.MemberEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MemberVOMapper : GenericMapper<MemberEntity, MemberVO> {
    companion object {
        val INSTANCE: MemberVOMapper = Mappers.getMapper(MemberVOMapper::class.java)
    }
}
