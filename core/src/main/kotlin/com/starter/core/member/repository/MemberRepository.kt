package com.starter.core.member.repository

import org.springframework.data.repository.CrudRepository

interface MemberRepository : CrudRepository<MemberEntity, Long> {

	fun findById(id : String) : MemberEntity?

}