package com.starter.admin.repository.user

import com.querydsl.jpa.impl.JPAQueryFactory
import com.starter.admin.app.user.model.UserSearchCondition
import com.starter.admin.repository.user.QUserEntity.userEntity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Component

interface UserRepository : JpaRepository<UserEntity, Long>, UserRepositoryQL {

    fun findByUuid(uuid: String): UserEntity?

    fun deleteByUuid(uuid: String)
}

interface UserRepositoryQL {
    fun findAllBy(condition: UserSearchCondition): List<UserEntity>
}

@Component
class UserRepositoryQLImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserRepositoryQL, QuerydslRepositorySupport(UserEntity::class.java) {
    override fun findAllBy(condition: UserSearchCondition): List<UserEntity> {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(
                condition.email?.let { userEntity.email.eq(it) },
                condition.userName?.let { userEntity.userName.eq(it) },
                condition.uuids?.let { userEntity.uuid.`in`(it) }
            )
            .fetch()
    }
}
