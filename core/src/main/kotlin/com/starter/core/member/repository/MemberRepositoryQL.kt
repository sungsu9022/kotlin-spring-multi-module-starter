package com.starter.core.member.repository

interface MemberRepositoryQL {
    fun findAllByName(name: String): List<MemberEntity>
}
