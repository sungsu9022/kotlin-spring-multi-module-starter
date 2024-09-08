package com.starter.core.clients.internal.admin.api

import com.starter.core.models.user.UserCreateRequest
import com.starter.core.models.user.UserPatchRequest
import com.starter.core.models.user.UserResponse
import com.starter.core.models.user.UserSearchRequest

interface UserApi {

    fun create(request: UserCreateRequest): UserResponse

    fun getUsers(request: UserSearchRequest) : List<UserResponse>

    fun getUser(uuid: String): UserResponse

    fun patchUser(uuid: String, request: UserPatchRequest)

    fun deleteUser(uuid: String)
}
