package com.starter.core.models.user


data class UserResponse(
    override val id: Long,
    override val uuid: String,
    override val userName: String,
    override val email: String,
    override val userStatus: UserStatus,
) : User
