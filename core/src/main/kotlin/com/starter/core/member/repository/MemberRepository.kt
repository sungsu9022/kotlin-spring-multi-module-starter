package com.starter.core.member.repository

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<MemberEntity, Long>, MemberRepositoryQL {

    fun findById(id: String): MemberEntity?

}
