package com.starter.api.app.member

import com.starter.api.app.member.service.MemberResisterService
import com.starter.core.common.response.ServiceResponse
import com.starter.core.common.response.SuccessResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberRestController(
    private val memberResisterService: MemberResisterService
) {

    @PostMapping("/api/members")
    fun createMember(@RequestBody memberDto: MemberDto): ServiceResponse {
        val memberVO = memberResisterService.resisterMember(memberDto)
        return SuccessResponse.create(memberVO)
    }

    @GetMapping("/api/members/{id}")
    fun getMember(@PathVariable id: String): ServiceResponse {
        val member = memberResisterService.getMemberById(id)
        return SuccessResponse.create(member)
    }

    @GetMapping("/api/members/name")
    fun getMembersByName(@RequestParam name: String): ServiceResponse {
        val members = memberResisterService.getMembersByName(name);
        return SuccessResponse.create(members)
    }
}

data class MemberDto(
    val name: String
)
