package com.starter.core.rdb.domain.user.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.starter.core.rdb.domain.user.repository.QUserEntity.userEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Component

interface UserRepository : JpaRepository<UserEntity, Long>, UserRepositoryQL {

    fun findByUuid(uuid: String): UserEntity?
}

interface UserRepositoryQL {
    fun findAllByName(name: String): List<UserEntity>
}

@Component
class UserRepositoryQLImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserRepositoryQL, QuerydslRepositorySupport(UserEntity::class.java) {
    override fun findAllByName(userName: String): List<UserEntity> {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userName.eq(userName))
            .fetch()
    }
}
