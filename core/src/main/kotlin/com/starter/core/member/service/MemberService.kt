package com.starter.core.member.service

import com.starter.core.common.exception.ErrorCode
import com.starter.core.common.exception.ServiceException
import com.starter.core.member.models.MemberVO
import com.starter.core.member.models.MemberVOMapper
import com.starter.core.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberVOMapper: MemberVOMapper
) {

    fun getMemberById(id: String): MemberVO {
        return memberRepository.findById(id)
            ?.let { memberVOMapper.convertToTarget(it) }
            ?: throw ServiceException(ErrorCode.NOT_FOUND)
    }

    fun getMembersByName(name: String): List<MemberVO> {
        return memberRepository.findAllByName(name)
            .map { memberVOMapper.convertToTarget(it) }
    }
}
