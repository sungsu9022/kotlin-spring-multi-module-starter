package com.starter.core.models.user

interface User {
    val id: Long
    val uuid: String
    val userName: String
    val email: String
    val userStatus: UserStatus
}
