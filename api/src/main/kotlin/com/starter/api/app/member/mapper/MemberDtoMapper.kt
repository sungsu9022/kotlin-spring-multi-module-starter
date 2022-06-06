package com.starter.api.app.member.mapper

import com.starter.api.app.member.MemberDto
import com.starter.core.common.mapper.GenericMapper
import com.starter.core.member.repository.MemberEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MemberDtoMapper : GenericMapper<MemberEntity, MemberDto> {
	companion object {
		val INSTANCE: MemberDtoMapper = Mappers.getMapper(MemberDtoMapper::class.java)
	}

	fun convertToEntity(target: MemberDto, id: String): MemberEntity {
		return MemberEntity(
			id = id,
			name = target.name
		)
	}


}

