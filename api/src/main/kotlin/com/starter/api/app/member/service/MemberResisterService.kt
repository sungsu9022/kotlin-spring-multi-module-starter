package com.starter.api.app.member.service

import com.starter.api.app.member.MemberDto
import com.starter.api.app.member.mapper.MemberDtoMapper
import com.starter.core.common.utils.IdUtils
import com.starter.core.member.models.MemberVO
import com.starter.core.member.models.MemberVOMapper
import com.starter.core.member.repository.MemberRepository
import com.starter.core.member.service.MemberService
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class MemberResisterService(
	private val memberRepository: MemberRepository,
	private val memberDtoMapper: MemberDtoMapper,
	private val memberVOMapper: MemberVOMapper,
) : MemberService(memberRepository, memberVOMapper) {
	companion object {
		private val logger = KotlinLogging.logger {}
	}

	fun resisterMember(memberDto: MemberDto) : MemberVO {
		val id = IdUtils.generate()
		val memberEntity = memberDtoMapper.convertToEntity(memberDto, id)
		logger.debug { "memberEntity : $memberEntity" }
		val saved = memberRepository.save(memberEntity)
		logger.debug { "memberEntity : $memberEntity, saved : $saved" }
		return memberVOMapper.convertToTarget(saved)
	}
}