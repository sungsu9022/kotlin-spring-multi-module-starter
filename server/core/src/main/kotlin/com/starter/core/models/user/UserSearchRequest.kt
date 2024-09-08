package com.starter.core.models.user

import com.fasterxml.jackson.annotation.JsonIgnore

data class UserSearchRequest(
    val email: String?,
    val userName: String?,
    val uuids: Set<String>?
) {

    @get:JsonIgnore
    val emptyRequest: Boolean
        get() = email == null
            && userName == null
            && uuids.isNullOrEmpty()
}
