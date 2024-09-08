package com.starter.admin.repository.user

import com.starter.core.models.user.User
import com.starter.core.models.user.UserStatus
import com.starter.core.rdb.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    @Column(columnDefinition = "CHAR(36)")
    override val uuid: String,
    override var userName: String,
    override var email: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", columnDefinition = "VARCHAR")
    override var userStatus: UserStatus
) : BaseEntity(), User
