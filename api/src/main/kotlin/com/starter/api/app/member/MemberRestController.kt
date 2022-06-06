package com.starter.api.app.member

import com.starter.api.app.member.service.MemberResisterService
import com.starter.core.common.response.ServiceResponse
import com.starter.core.common.response.SuccessResponse
import org.springframework.web.bind.annotation.*

@RestController
class MemberRestController(
	private val memberResisterService: MemberResisterService
) {

	@PostMapping("/api/members")
	fun createMember(@RequestBody memberDto: MemberDto) : ServiceResponse {
		val memberVO = memberResisterService.resisterMember(memberDto)
		return SuccessResponse.create(memberVO)
	}

	@GetMapping("/api/members/{id}")
	fun createMember(@PathVariable id: String) : ServiceResponse {
		val member = memberResisterService.getMemberById(id)
		return SuccessResponse.create(member)
	}
}

data class MemberDto(
	val name: String
)