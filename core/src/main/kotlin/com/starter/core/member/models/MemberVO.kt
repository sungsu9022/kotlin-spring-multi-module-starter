package com.starter.core.member.models

import java.time.LocalDateTime


data class MemberVO(
	override val id: String,
	override val name: String,
	val registerYmdt : LocalDateTime,
	val modifyYmdt : LocalDateTime,
) : Member