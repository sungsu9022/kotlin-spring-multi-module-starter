package com.starter.core.member.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class MemberRepositoryQLImpl(
    private val queryFactory: JPAQueryFactory
) : MemberRepositoryQL, QuerydslRepositorySupport(MemberEntity::class.java) {
    override fun findAllByName(name: String): List<MemberEntity> {
        return queryFactory.selectFrom(QMemberEntity.memberEntity)
            .where(QMemberEntity.memberEntity.name.eq(name))
            .fetch()
    }
}
