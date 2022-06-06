package com.starter.core.member.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.starter.core.common.querydsl.AbstractQuerydslRepositorySupport
import com.starter.core.member.repository.QMemberEntity.memberEntity
import org.springframework.stereotype.Repository

@Repository
class MemberRepositorySupport(
	private val queryFactory: JPAQueryFactory
) : AbstractQuerydslRepositorySupport(MemberEntity::class.java) {
	fun findAllByName(name: String): List<MemberEntity> {
		return queryFactory.selectFrom(memberEntity)
			.where(memberEntity.name.eq(name))
			.fetch()
	}

}
