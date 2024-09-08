package com.starter.admin.app.user.model

data class UserSearchCondition(
    val email: String?,
    val userName: String?,
    val uuids: Set<String>?
)
